package com.log.log4j.multienviroments.log4.jmultienviroments.user;

import org.apache.log4j.Logger;

public class User {
    private Logger logger=Logger.getLogger(User.class);
    public void userMath(){
        try {
            int i=1/0;
        }catch (Exception e){
            logger.info(">>>>>>>>> >>>异常："+e.getMessage());
            logger.debug(">>>>>>>>>>>>异常："+e.getMessage());
            logger.error(">>>>>>>>>>>>异常："+e.getMessage(),e);
        }
    }
}
