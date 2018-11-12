package oracle.mediator.callout.sample;

import java.util.logging.Logger;


import oracle.tip.mediator.common.api.AbstractJavaCalloutImpl;
import oracle.tip.mediator.common.api.CalloutMediatorMessage;
import oracle.tip.mediator.common.api.IJavaCallout;
import oracle.tip.mediator.common.api.MediatorCalloutException;
import oracle.tip.mediator.metadata.CaseType;

public class MediatorCalloutImpl extends AbstractJavaCalloutImpl implements IJavaCallout{
    
    Logger logger = Logger.getLogger("Callout");
    public MediatorCalloutImpl() {
    }
    
    public void initialize(Logger logger) throws MediatorCalloutException{        
        this.logger = logger;
        this.logger.info("Callout: Initializing...");
    }
    
    public boolean preRouting(CalloutMediatorMessage message) throws MediatorCalloutException{
        logger.info("Callout: Pre routing..."+message.getComponentDN());
        return false;
    }
    
    public boolean postRouting(CalloutMediatorMessage message,CalloutMediatorMessage response,Throwable e) throws MediatorCalloutException{
        logger.info("Callout: Post routing..."+message.getComponentDN()); 
        return false;
    }

    public boolean preRoutingRule(CaseType caseType,
                                  CalloutMediatorMessage externalMediatorMessage) {
        logger.info("Callout: Pre routing rule..."+caseType.getName());
        return true;
    }

    public boolean postRoutingRule(CaseType caseType,
                                   CalloutMediatorMessage externalMediatorMessage,
                                   CalloutMediatorMessage externalMediatorMessage1,
                                   Throwable throwable) {
        logger.info("Callout: Post routing rule..."+caseType.getName());
        return true;
    }
}
