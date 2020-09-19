package com.bosssoft.ecds.service.impl;

import com.bosssoft.ecds.dao.FbeStockInDao;
import com.bosssoft.ecds.domain.po.FbeStockInPO;
import com.bosssoft.ecds.domain.vo.FbeStockInVO;
import com.bosssoft.ecds.service.FbeStockInService;
import com.bosssoft.ecds.utils.POToVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 入库票据的service层，处理业务逻辑和实体类转换
 * @author zouyou
 * @version 1.0
 * @date 2020/8/17 16:54
 */
@Service
public class FbeStockInServiceImpl implements FbeStockInService {

    @Autowired
    private FbeStockInDao fbeStockInDao;

    /**
     * 获取所有的入库信息，将PO转成VO类，返回一个VO的list集合
     * @author zouyou
     * @return java.util.List<com.bosssoft.ecds.domain.vo.FbeStockInVO>
     * @date 2020/8/25 9:03
     */
    @Override
    public List<FbeStockInVO> getList(){
        List<FbeStockInPO> fbeStockInPOList= fbeStockInDao.selectList(null);
        List<FbeStockInVO> fbeStockInVOList = new ArrayList<>();
        for (FbeStockInPO fbeStockInPO : fbeStockInPOList) {
            fbeStockInVOList.add(POToVO.stockInPOToVO(fbeStockInPO));
        }
        return fbeStockInVOList;
    }

    /**
     * 查询入库各票据种类的票据数量，将PO转成VO类，返回一个VO的list集合
     * @author zouyou
     * @return java.util.List<com.bosssoft.ecds.domain.vo.FbeStockInVO>
     * @date 2020/8/25 9:03
     */
    @Override
    public List<FbeStockInVO> selectNum() {
        List<FbeStockInPO> fbeStockInPOList= fbeStockInDao.selectNum();
        List<FbeStockInVO> fbeStockInVOList = new ArrayList<>();
        for (FbeStockInPO fbeStockInPO : fbeStockInPOList) {
            fbeStockInVOList.add(POToVO.stockInPOToVO(fbeStockInPO));
        }
        return fbeStockInVOList;
    }

    /**
     * 查询入库的时间与数量对应的信息，返回一个map集合
     * @author zouyou
     * @return java.util.Map<java.lang.String, int [ ]>
     * @date 2020/8/25 9:05
     */
    @Override
    public Map<String, int[]> selectBar() {
        return getBarMap(fbeStockInDao);
    }

    /**
     * 查询近七天的入库票据数量，返回一个list
     * @author zouyou
     * @return java.util.List<java.lang.Integer>
     * @date 2020/8/25 9:05
     */
    @Override
    public List<Integer> selectNumIndex() {
        return fbeStockInDao.selectNumIndex();
    }

    /**
     * 获取与前端对应数据格式的每年每月对应的入库票据数量，以map返回
     * @param fbeStockInDao
     * @author zouyou
     * @return java.util.Map<java.lang.String, int [ ]>
     * @date 2020/8/25 9:06
     */
    public Map<String,int[]> getBarMap(FbeStockInDao fbeStockInDao) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, int[]> map = new HashMap<>();
        int[][] monthArray = new int[7][12];
        for (FbeStockInPO fbeStockInPO : fbeStockInDao.selectBar()) {
            String time = sdf.format(fbeStockInPO.getFCreateTime());
            int number = fbeStockInPO.getFNumber();
            String year = time.substring(0, 4);
            String month = time.substring(5, 7);
            switch (year) {
                case "2014":
                    setNumber(month, number, monthArray[0]);
                    map.put("2014", monthArray[0]);
                    break;
                case "2015":
                    setNumber(month, number, monthArray[1]);
                    map.put("2015", monthArray[1]);
                    break;
                case "2016":
                    setNumber(month, number, monthArray[2]);
                    map.put("2016", monthArray[2]);
                    break;
                case "2017":
                    setNumber(month, number, monthArray[3]);
                    map.put("2017", monthArray[3]);
                    break;
                case "2018":
                    setNumber(month, number, monthArray[4]);
                    map.put("2018", monthArray[4]);
                    break;
                case "2019":
                    setNumber(month, number, monthArray[5]);
                    map.put("2019", monthArray[5]);
                    break;
                case "2020":
                    setNumber(month, number, monthArray[6]);
                    map.put("2020", monthArray[6]);
                    break;
                default:
                    break;
            }
        }
        return map;
    }

    /**
     * 设置每个月的入库对应数量，放入传进的数组里
     * @param str
     * @param number
     * @param arr
     * @author zouyou
     * @return void
     * @date 2020/8/25 9:07
     */
    public void setNumber(String str, int number, int[] arr) {
        switch (str) {
            case "01":
                arr[0] += number;
                break;
            case "02":
                arr[1] += number;
                break;
            case "03":
                arr[2] += number;
                break;
            case "04":
                arr[3] += number;
                break;
            case "05":
                arr[4] += number;
                break;
            case "06":
                arr[5] += number;
                break;
            case "07":
                arr[6] += number;
                break;
            case "08":
                arr[7] += number;
                break;
            case "09":
                arr[8] += number;
                break;
            case "10":
                arr[9] += number;
                break;
            case "11":
                arr[10] += number;
                break;
            case "12":
                arr[11] += number;
                break;
            default:
                break;
        }
    }
}
