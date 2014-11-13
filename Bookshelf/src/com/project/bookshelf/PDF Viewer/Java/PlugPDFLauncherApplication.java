package project.bookshelf;

/**
 * Created by Kevan Lester on 11/12/2014.
 * Plug pdf requires a key to activate. It must match the package name of your project
 * for package project.bookshelf the key is
 * 56F52DE37GB7C69DED34BEBCBFFBG92D7C24D27D2BCC6GCDG3BEFBF5
 * for com.project.bookshelf the key is
 * 46F52D83GG988H46ED34G44CCF3CCD46GEFHC68AD8834BDBCG5GFBF5
 *
 */
import android.app.Application;
import android.util.Log;

import com.epapyrus.plugpdf.core.PlugPDF;
import com.epapyrus.plugpdf.core.PlugPDFException.InvalidLicense;
import com.epapyrus.plugpdf.core.PlugPDFException.LicenseMismatchAppID;
import com.epapyrus.plugpdf.core.PlugPDFException.LicenseTrialTimeOut;
import com.epapyrus.plugpdf.core.PlugPDFException.LicenseUnusableOS;
import com.epapyrus.plugpdf.core.PlugPDFException.LicenseWrongProductVersion;
public class PlugPDFLauncherApplication  extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        try {
            PlugPDF.init(getApplicationContext(),
                    "56F52DE37GB7C69DED34BEBCBFFBG92D7C24D27D2BCC6GCDG3BEFBF5");
            PlugPDF.deployAssetFontResource(getApplicationContext());
            PlugPDF.enableUncaughtExceptionHandler();
        } catch (LicenseWrongProductVersion ex) {
            Log.d("LicenseEx", "LicenseWrongProductVersion");
        } catch (LicenseTrialTimeOut ex) {
            Log.d("LicenseEx", "LicenseTrialTimeOut");
        } catch (LicenseUnusableOS ex) {
            Log.d("LicenseEx", "LicenseUnusableOS");
        } catch (LicenseMismatchAppID ex) {
            Log.d("LicenseEx", "LicenseMismatchAppID");
        } catch (InvalidLicense ex) {
            Log.d("LicenseEx", "Invalid License");
        } catch (Exception ex) {
            Log.d("Exception", (ex.getMessage() == null) ? "Unknown Error!" : ex.getMessage());
        }
    }
}
