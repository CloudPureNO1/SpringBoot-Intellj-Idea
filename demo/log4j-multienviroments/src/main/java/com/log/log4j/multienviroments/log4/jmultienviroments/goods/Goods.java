package com.log.log4j.multienviroments.log4.jmultienviroments.goods;

import org.apache.log4j.Logger;

public class Goods {
    private static final Logger logger=Logger.getLogger(Goods.class);

    public void goodsMath(){
        try {
            int i=1/0;
        }catch (Exception e){
            logger.info(">>>>>>>>> >>>异常："+e.getMessage());
            logger.debug(">>>>>>>>>>>>异常："+e.getMessage());
            logger.error(">>>>>>>>>>>>异常："+e.getMessage(),e);
        }
    }


}
