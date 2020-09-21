package com.lzy.imagepickerdemo.data;

import java.util.Arrays;
import java.util.List;

public class Images {

    private static String[] sTestImages;

    static {

        sTestImages = new String[50];
        for (int i = 0; i < 50; i++) {
            sTestImages[i] = "http://www.android-cube-app-server.liaohuqiu.net/test-image/" + i + ".thumb.png";
        }
    }

    public static List<String> getImages() {
        return Arrays.asList(Images.imageUrls);
    }

    private final static String[] imageUrls = new String[]{
            "https://c-ssl.duitang.com/uploads/blog/202009/10/20200910090708_75026.thumb.1000_0.jpg",
            "https://c-ssl.duitang.com/uploads/blog/202009/10/20200910090709_5f899.thumb.1000_0.jpg",
            "https://c-ssl.duitang.com/uploads/item/201306/14/20130614185903_raNR3.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/item/201112/11/20111211191621_HU4Bj.thumb.1000_0.jpg",
            "https://c-ssl.duitang.com/uploads/item/201408/07/20140807224553_VXaUc.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201407/29/20140729105929_GQLwC.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201408/04/20140804160432_LnFeB.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201408/04/20140804161101_JVJea.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201408/04/20140804093210_FxFNd.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201408/04/20140804160314_hRKtv.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/item/201408/01/20140801080524_SnGfE.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/item/201407/23/20140723140958_NSWfE.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201407/22/20140722153305_WHejQ.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/item/201407/21/20140721010148_ZBQwe.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/item/201407/23/20140723085122_cmteu.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/item/201407/23/20140723130620_Z2yJB.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201407/20/20140720204738_NXxLE.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/item/201407/20/20140720134916_VGfyh.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201407/17/20140717113117_mUssJ.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201407/17/20140717121501_wfFEm.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201407/17/20140717121431_w4AV8.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201407/17/20140717121918_TtJjY.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/item/201407/11/20140711234806_FNLBQ.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/item/201407/18/20140718121437_UyiAS.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201407/17/20140717114829_RiCXR.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201407/17/20140717120301_wGFYL.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201405/11/20140511121106_JXS4B.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201405/10/20140510094144_kfLji.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201405/09/20140509201906_kERjy.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201405/08/20140508193226_UaSGB.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201405/05/20140505201747_aPNtf.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201405/06/20140506104824_jPWQj.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201405/05/20140505201105_MkXdn.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201405/03/20140503205822_GCzta.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201405/03/20140503205535_cCHPB.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201405/03/20140503204354_xxSQX.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201404/06/20140406191307_GTxFd.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201404/06/20140406191247_BG2cU.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201404/06/20140406191114_MzYtw.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201404/06/20140406191127_fazJA.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/item/201407/10/20140710081204_vYnCi.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/item/201407/15/20140715133758_M2Y3J.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/item/201407/13/20140713190806_TGJHm.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201407/05/20140705223413_5r3ze.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/item/201407/13/20140713012526_tcy5u.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201407/13/20140713121424_Gy43T.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/item/201407/15/20140715033844_tcvrY.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/item/201407/10/20140710181106_4HHay.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201407/06/20140706122850_8PER3.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201407/07/20140707192042_8xKXF.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/item/201407/07/20140707063954_mVa3y.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/item/201407/08/20140708093733_AFFhc.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201407/07/20140707161220_unvzn.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201407/07/20140707113856_hBf3R.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/item/201308/26/20130826090203_NtuYA.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/item/201407/07/20140707145925_dHeKV.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/item/201406/25/20140625101534_xiZxN.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201406/30/20140630150534_EWUVY.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/item/201406/25/20140625121626_ZmT5n.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201307/31/20130731231806_4yGxV.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/item/201406/28/20140628122218_fLQyP.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201406/26/20140626131831_MrdKP.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/blog/201406/16/20140616165201_nuKWj.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/item/201406/25/20140625140308_KP4rn.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/item/201406/25/20140625121604_2auuA.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/item/201406/25/20140625131625_LmmLZ.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/item/201406/25/20140625132851_mPmKY.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/item/201406/25/20140625133312_ZtmW4.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/item/201406/25/20140625164858_AuafS.thumb.1000_0.jpeg",
            "https://c-ssl.duitang.com/uploads/item/201406/24/20140624114145_e4iVw.thumb.1000_0.jpeg",
            
    };
}

