package core2.maz.com.core2.responsemodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by Ankur Jain on 11-11-2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppFeedResponseModel extends MasterResponse
{


        @JsonProperty("sections")
        private ArrayList<SectionResponseModel> sections;

        @JsonProperty("modified")
        private String modified;

        @JsonProperty("primaryColor")
        private String primaryThemeColor;

        @JsonProperty("secondaryColor")
        private String secondaryThemeColor;

        @JsonProperty("sponsor")
        private SponsorResponseModel sponsor;

        @JsonProperty("banners")
        private ArrayList<BannerModel> banners;

        @JsonProperty("subscriptionTitle")
        private String subscriptionTitle;

        @JsonProperty("subscriptionLabel")
        private String subscriptionLabel;

        @JsonProperty("subscriptionAllAccess")
        private boolean subscriptionAllAccess;

        @JsonProperty("subscriptions")
        private ArrayList<SubscriptionModel> subscriptionModels;

        @JsonProperty("hideSubscriberLogin")
        private boolean hideSubscriberLogin;

        @JsonProperty("logo")
        private ArticleCoverModel logo;

        public void setSections(ArrayList<SectionResponseModel> sections) {
            this.sections = sections;
        }


        public ArrayList<SectionResponseModel> getSections() {
            return sections;
        }

        public String getModified() {
            return modified;
        }


        public SponsorResponseModel getSponsor() {
            return sponsor;
        }

        public ArrayList<BannerModel> getBanners() {
            return banners;
        }

        public String getSubscriptionTitle() {
            return subscriptionTitle;
        }

        public String getSubscriptionLabel() {
            return subscriptionLabel;
        }

        public boolean isSubscriptionAllAccess() {
            return subscriptionAllAccess;
        }

        public boolean isHideSubscriberLogin() {
            return hideSubscriberLogin;
        }

        public ArrayList<SubscriptionModel> getSubscriptionModels() {
            return subscriptionModels;
        }

        public String getPrimaryThemeColor() {
            return primaryThemeColor;
        }

        public String getSecondaryThemeColor() {
            return secondaryThemeColor;
        }

        public ArticleCoverModel getLogo() {
            return logo;
        }

}
