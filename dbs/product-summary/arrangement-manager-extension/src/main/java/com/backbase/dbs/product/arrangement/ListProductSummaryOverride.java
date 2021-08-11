package com.backbase.dbs.product.arrangement;

import com.backbase.dbs.arrangement.client.model.CurrentAccountProductKindsDto;
import com.backbase.dbs.arrangement.client.model.SummaryAggregatedBalanceDto;
import com.backbase.dbs.product.Configurations;
import com.backbase.dbs.product.balance.AggregatedBalanceCurrency;
import com.backbase.dbs.product.balance.BalanceService;
import com.backbase.dbs.product.clients.AccessControlClient;
import com.backbase.dbs.product.clients.JwtContext;
import com.backbase.dbs.product.common.util.ErrorKey;
import com.backbase.dbs.product.persistence.Arrangement;
import com.backbase.dbs.product.persistence.Arrangement_;
import com.backbase.dbs.product.repository.ArrangementJpaRepository;
import com.backbase.dbs.product.repository.arrangement.ArrangementSearchParams;
import com.backbase.dbs.product.summary.ProductSummary;
import com.backbase.dbs.product.summary.ProductSummaryFilter;
import com.backbase.dbs.product.summary.ProductSummaryService;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.persistence.metamodel.Attribute;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Primary
@Service
public class ListProductSummaryOverride extends ProductSummaryService {
    private static final Logger log = LoggerFactory.getLogger(ProductSummaryService.class);
    private final Configurations configurations;
    private final ArrangementService arrangementService;
    private final JwtContext jwtContext;
    private final AccessControlClient accessControlClient;
    private final ArrangementJpaRepository arrangementRepository;
    private final BalanceService balanceService;

    public ListProductSummaryOverride(Configurations configurations, ArrangementService arrangementService, JwtContext jwtContext, AccessControlClient accessControlClient, ArrangementJpaRepository arrangementRepository, BalanceService balanceService) {
        super(configurations, arrangementService, jwtContext, accessControlClient, arrangementRepository, balanceService);
        this.configurations = configurations;
        this.arrangementService = arrangementService;
        this.jwtContext = jwtContext;
        this.accessControlClient = accessControlClient;
        this.arrangementRepository = arrangementRepository;
        this.balanceService = balanceService;
    }

    @Override
    public ProductSummary getProductSummary(@NotNull ProductSummaryFilter filter) {
        String userId = this.jwtContext.getUserId();
        List<String> arrangementIds = this.accessControlClient.getArrangementIdsByUserIdWithViewPrivilege(userId);
        List<Arrangement> arrangements = Lists.newArrayList();
        if (!arrangementIds.isEmpty()) {
            arrangements = this.arrangementRepository.search(ArrangementSearchParams
                    .builder().subEntities(Sets.newHashSet(new Attribute[]{Arrangement_.userPreferences})).debitAccount(filter.getDebitAccount()).creditAccount(filter.getCreditAccount()).ids(Lists.newArrayList(arrangementIds)).ignoredProductKindNames((Set) Optional.ofNullable(filter.getIgnoredProductKindNames()).orElse(this.configurations.getFilteredOutProductKinds())).locale(filter.getLocale()).userId(userId).build());
            log.debug("Got {} arrangements", ((List) arrangements).size());
            if (this.configurations.isPullLatestBalance()) {
                this.balanceService.updateBalancesFromCoreBank((Collection) arrangements);
            }
        } else if (this.configurations.isForbiddenOnEmpty()) {
            throw ErrorKey.ERR_NO_PRIVILEGE.toForbidden();
        }

        return this.createProductSummary((List) arrangements, userId);
    }

    private ProductSummary createProductSummary(List<Arrangement> arrangements, String userId) {
        ProductSummary productSummary = new ProductSummary(userId);

        this.arrangementService.updateDebitCardsFromCoreBank(productSummary.getCurrentAccounts());
        this.arrangementService.updateDebitCardsFromCoreBank(productSummary.getDebitCards());
        CurrentAccountProductKindsDto currentAccountDto= new CurrentAccountProductKindsDto();
        productSummary.getCurrentAccountsSummary().setAggregatedBalance(copyAggregatedBalance(currentAccountDto.getAggregatedBalance()));
        return productSummary;
    }


    private AggregatedBalanceCurrency copyAggregatedBalance(SummaryAggregatedBalanceDto balance) {
        AggregatedBalanceCurrency aggregatedBalance = AggregatedBalanceCurrency.builder().build();
        aggregatedBalance.setAmount(BigDecimal.valueOf(Long.parseLong(balance.getValue())));
        aggregatedBalance.setCurrencyCode(balance.getCurrency());
        return aggregatedBalance;
    }
}
