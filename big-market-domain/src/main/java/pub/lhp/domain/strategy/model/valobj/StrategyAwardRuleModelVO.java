package pub.lhp.domain.strategy.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pub.lhp.domain.strategy.service.rule.filter.factory.DefaultLogicFactory;
import pub.lhp.types.common.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lihaopeng
 * @version 1.0
 * @project big-market
 * @description 抽奖策略规则规则值对象；值对象，没有唯一ID，仅限于从数据库查询对象
 * @since 2024/10/3 19:04
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyAwardRuleModelVO {

    private String ruleModels;

    public String[] raffleCenterRuleModelList() {
//        List<String> ruleModelList = new ArrayList<>();
//        String[] ruleModelValues = ruleModels.split(Constants.SPLIT);
//        for (String ruleModelValue : ruleModelValues) {
//            if (DefaultLogicFactory.LogicModel.isCenter(ruleModelValue)) {
//                ruleModelList.add(ruleModelValue);
//            }
//        }
        List<String> ruleModelList = Arrays.stream(ruleModels.split(Constants.SPLIT))
                .filter(DefaultLogicFactory.LogicModel::isCenter)
                .collect(Collectors.toList());

        return ruleModelList.toArray(new String[0]);
    }

    public String[] raffleAfterRuleModelList() {
        List<String> ruleModelList = new ArrayList<>();
        String[] ruleModelValues = ruleModels.split(Constants.SPLIT);
        for (String ruleModelValue : ruleModelValues) {
            if (DefaultLogicFactory.LogicModel.isAfter(ruleModelValue)) {
                ruleModelList.add(ruleModelValue);
            }
        }
        return ruleModelList.toArray(new String[0]);
    }
}
