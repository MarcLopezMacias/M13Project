package cat.itb.m13project.provider;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;

import androidx.fragment.app.Fragment;

import cat.itb.m13project.R;

import static cat.itb.m13project.ConstantVariables.LOCAL_FILE_PATH;
import static cat.itb.m13project.ConstantVariables.PROVIDER_STOCK_URL;
import static cat.itb.m13project.ConstantVariables.STOCK_FILE_NAME;
import static cat.itb.m13project.ConstantVariables.UPDATING_STOCK;


public class ProviderWebServices extends Fragment {

    public boolean customMethod() {
        downloadFile();
        return true
//                 updateDatabase(source)
//                && deleteFile(source)
                ;
    }

    public void downloadFile() {
        boolean isOk;
        System.out.println(PROVIDER_STOCK_URL);
        System.out.println(LOCAL_FILE_PATH);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(PROVIDER_STOCK_URL));
        request.setDescription(UPDATING_STOCK);
        request.setTitle(getString(R.string.app_name));

        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, STOCK_FILE_NAME);

        DownloadManager manager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

}

