package VDSTDBINSChannelPartnerListStaging;

import java.sql.Timestamp;

public class VdstChnlpartnerMaster {

	private String channelPartnerId;
	private String language;
	private String channelPartnerDescription;
	private Timestamp generatedTimestamp;

public VdstChnlpartnerMaster() {
	super();
}

public String getChannelPartnerDescription() {
	return this.channelPartnerDescription;
}

public String getChannelPartnerId() {
	return this.channelPartnerId;
}

public Timestamp getGeneratedTimestamp() {
	return this.generatedTimestamp;
}

public String getLanguage() {
	return this.language;
}

public void setChannelPartnerDescription(String channelPartnerDescription) {
	this.channelPartnerDescription = channelPartnerDescription;
}

public void setChannelPartnerId(String channelPartnerId) {
	this.channelPartnerId = channelPartnerId;
}

public void setGeneratedTimestamp(Timestamp generatedTimestamp) {
	this.generatedTimestamp = generatedTimestamp;
}

public void setLanguage(String language) {
	this.language = language;
}

}
