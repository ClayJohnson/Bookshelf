package project.bookshelf;

/**
 * Created by Kevan Lester on 11/12/2014.
 * PDfReader is implemented through a 3rd party API because of android lack of native PDF support
 * PDFReader takes a pdf file and path and converts it into Byte Data and then passes it to a
 * reader factory for rendering
 */
import java.io.InputStream;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.os.Bundle;

import com.epapyrus.plugpdf.SimpleDocumentReader;
import com.epapyrus.plugpdf.SimpleDocumentReaderListener;
import com.epapyrus.plugpdf.SimpleReaderFactory;
import com.epapyrus.plugpdf.core.PDFDocument;
import com.epapyrus.plugpdf.core.PropertyManager;
import com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay.PageDisplayMode;
import com.epapyrus.plugpdf.core.viewer.DocumentState;


public class SimpleReaderActivity extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PropertyManager.setScrollFrictionCoef(1);
        PropertyManager.setScrollVelocityCoef(1);
        PropertyManager.setPreviewQualityCoef(1.5);


        PDFDocument.setFontPath("/mnt/sdcard");
        PDFDocument.setFontMap("Malgun=malgun.ttf:*=malgun.ttf");
    // place pdf filename and path variable in place of PDF filename in statement below
        String fileName = new String("FullParentHandout.pdf");

         AssetManager am = getResources().getAssets();

        try {
            InputStream is = am.open(fileName);

            int size = is.available();
            if (size > 0) {
                byte[] data = new byte[size];
                is.read(data);
                open(data, fileName);

            }

            is.close();
        } catch (Exception ex) {
        }

    }


    SimpleDocumentReader mViewer;

// Usr byte data to create reader factory
    protected void open(byte[] data, String fileName) {
        mViewer = SimpleReaderFactory.createSimpleViewer(this, mViewerListener);
        mViewer.openData(data, data.length, "");
        mViewer.enableAnnotationMenu(false);
        //mViewer.setTitle(fileName);
        mViewer.setPageDisplayMode(PageDisplayMode.HORIZONTAL);
    }
// uses pdf path and filename to create reader factory. Not as efficient as byte data
    protected void open(String path) {
        mViewer = SimpleReaderFactory.createSimpleViewer(this, mViewerListener);
        mViewer.openFile(path, "");
        mViewer.enableAnnotationMenu(false);
       // mViewer.setTitle("Sample");
        mViewer.setPageDisplayMode(PageDisplayMode.HORIZONTAL);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        mViewer.refreshLayout();
        super.onConfigurationChanged(newConfig);
    }

    SimpleDocumentReaderListener mViewerListener = new SimpleDocumentReaderListener() {

        @Override
        public void onLoadFinish(DocumentState.OPEN state) {

        }
    };
}
