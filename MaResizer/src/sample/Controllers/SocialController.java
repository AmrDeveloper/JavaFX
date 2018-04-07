package sample.Controllers;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public final class SocialController {

    //My Social Media Links
    private final String FACEBOOK = "https://www.facebook.com/AmrDeveloper";
    private final String GITHUB = "https://www.github.com/AmrDeveloper";
    private final String LINKEDIN = "https://www.linkedin.com/in/AmrDeveloper";
    private final String ASKFM = "https://ask.fm/AmrDeveloper";

    /**
     * Desktop Object to control Desktop Events
     */
    private final Desktop mCurrentDesktop = Desktop.getDesktop();

    /**
     * Open My Facebook Profile
     */
    private void openFacebookProfile() {
        URI facebook = stringLinkToURI(FACEBOOK);
        try {
            this.mCurrentDesktop.browse(facebook);
        } catch (IOException ioEx) {
            Debugger.showErrorDialog("SocialMedia","Can't Open Link");
        }
    }

    /**
     * Open My Linkedin Profile
     */
    private void openLinkedInProfile() {
        URI facebook = stringLinkToURI(LINKEDIN);
        try {
            this.mCurrentDesktop.browse(facebook);
        } catch (IOException ioEx) {
            Debugger.showErrorDialog("SocialMedia","Can't Open Link");
        }
    }

    /**
     * Open My Github Profile
     */
    private void openGithubProfile() {
        URI facebook = stringLinkToURI(GITHUB);
        try {
            this.mCurrentDesktop.browse(facebook);
        } catch (IOException ioEx) {
            Debugger.showErrorDialog("SocialMedia","Can't Open Link");
        }
    }

    /**
     * Open My Ask.fm Profile
     */
    private void openAskFmProfile() {
        URI facebook = stringLinkToURI(ASKFM);
        try {
            this.mCurrentDesktop.browse(facebook);
        } catch (IOException ioEx) {
            Debugger.showErrorDialog("SocialMedia","Can't Open Link");
        }
    }

    /**
     * @param linkString : the Url of My Profile as String
     * @return : the url as URI Object
     */
    private URI stringLinkToURI(String linkString) {
        URI linkURI = null;
        try {
            linkURI = new URI(linkString);
        } catch (URISyntaxException exception) {
        }
        return linkURI;
    }


}
