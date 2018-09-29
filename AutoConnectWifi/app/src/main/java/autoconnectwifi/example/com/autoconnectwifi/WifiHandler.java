package autoconnectwifi.example.com.autoconnectwifi;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

/**
 * Created by deepak on 8/3/17.
 */

public class WifiHandler {


    private static Context context = null;

    private static WifiHandler instance = null;

    private WifiConfiguration conf = null;

    private WifiManager wifiManager = null;

    private static String networkSSID = "iPhone";
    private static String networkPass = "12345678";

    public void init()
    {
        conf = new WifiConfiguration();
        conf.SSID = "\"" + networkSSID + "\"";   // Please note the quotes. String should contain ssid in quotes
        conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
        wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        wifiManager.addNetwork(conf);

        autoConnect();
    }


    public void autoConnect()
    {
        List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
        for( WifiConfiguration i : list ) {
            if(i.SSID != null && i.SSID.equals("\"" + networkSSID + "\"")) {

                showToast("Connecting Open WIfi",Toast.LENGTH_SHORT);
                wifiManager.disconnect();
                wifiManager.enableNetwork(i.networkId, true);
                wifiManager.reconnect();

                describleWifiInfo();
                break;
            }
        }
    }

    public void describleWifiInfo()
    {
        showToast(wifiManager.getConnectionInfo().toString(),Toast.LENGTH_LONG);
    }

    public void showToast(String info,int duration)
    {
        Toast.makeText(context,info,duration);
    }

    public static WifiHandler getInstance(Context pcontext){
        context = pcontext;
        if (instance!=null) {
            return instance;
        }
        instance = new WifiHandler();
        instance.init();
        return instance;
    }

    /*
    public void connectToSSID(final String ssid, final String key) {
        Log.i("wifimaster", "connection to "+ssid);

        WifiConfiguration wc = new WifiConfiguration();
        wc.SSID = "\""+ssid+"\""; //IMPORTANT! This should be in Quotes!!
        wc.priority = 40;
        wc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
        wc.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
        wc.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
        wc.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
        wc.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
        wc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
        wc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
        wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
        wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
        wc.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
        wc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
        wc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_EAP);
        wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
        wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);

        wc.preSharedKey = "\""+key+"\"";
        wc.wepKeys[0] = "\""+key+"\""; //This is the WEP Password
        wc.wepTxKeyIndex = 0;

        wc.preSharedKey = "\""+key+"\"";

        int res = wifiManager.addNetwork(wc);
        Log.d("WifiPreference", "add Network returned " + res );
        boolean es = wifiManager.saveConfiguration();
        Log.d("WifiPreference", "saveConfiguration returned " + es );
        boolean b = wifiManager.enableNetwork(res, true);
        Log.d("WifiPreference", "enableNetwork returned " + b );

        if(context)
        {
            if(b)
                Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(context, "Not Connected", Toast.LENGTH_SHORT).show();
        }

    }*/
}
