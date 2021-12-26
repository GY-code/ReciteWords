package com.bjtu.recitewords.discover.util;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.bjtu.recitewords.discover.bean.TiktokBean;
import com.bjtu.recitewords.discover.bean.VideoBean;

public class DataUtil {

    public static final String SAMPLE_URL = "http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4";
//    public static final String SAMPLE_URL = "file:///mnt/sdcard/out.webm";

//    public static List<VideoBean> getVideoList() {
//        List<VideoBean> videoList = new ArrayList<>();
//        videoList.add(new VideoBean("七舅脑爷| 脑爷烧脑三重奏，谁动了我的蛋糕",
//                "http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2018/03/2018-03-30_10-1782811316-750x420.jpg",
//                "http://cdnxdc.tanzi88.com/XDC/dvideo/2018/03/29/8b5ecf95be5c5928b6a89f589f5e3637.mp4"));
//
//        videoList.add(new VideoBean("七舅脑爷| 你会不会在爱情中迷失了自我，从而遗忘你正拥有的美好？",
//                "http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2018/02/2018-02-09_23-573150677-750x420.jpg",
//                "http://cdnxdc.tanzi88.com/XDC/dvideo/2018/02/29/056bf3fabc41a1c1257ea7f69b5ee787.mp4"));
//
//        videoList.add(new VideoBean("七舅脑爷| 别因为你的患得患失，就怀疑爱情的重量",
//                "http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2018/02/2018-02-23_57-2208169443-750x420.jpg",
//                "http://cdnxdc.tanzi88.com/XDC/dvideo/2018/02/29/db48634c0e7e3eaa4583aa48b4b3180f.mp4"));
//
//        videoList.add(new VideoBean("七舅脑爷| 女员工遭老板调戏，被同事陷害，双面夹击路在何方？",
//                "http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/12/2017-12-08_39-829276539-750x420.jpg",
//                "http://cdnxdc.tanzi88.com/XDC/dvideo/2017/12/29/fc821f9a8673d2994f9c2cb9b27233a3.mp4"));
//
//        videoList.add(new VideoBean("七舅脑爷| 夺人女友，帮人作弊，不正经的学霸比校霸都可怕。",
//                "http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2018/01/2018-01-05_49-2212350172-750x420.jpg",
//                "http://cdnxdc.tanzi88.com/XDC/dvideo/2018/01/29/bc95044a9c40ec2d8bdf4ac9f8c50f44.mp4"));
//
//        videoList.add(new VideoBean("七舅脑爷| 男子被困秘密房间上演绝命游戏, 背后凶手竟是他?",
//                "http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/11/2017-11-10_10-320769792-750x420.jpg",
//                "http://cdnxdc.tanzi88.com/XDC/dvideo/2017/11/29/15f22f48466180232ca50ec25b0711a7.mp4"));
//
//        videoList.add(new VideoBean("七舅脑爷| 男人玩心机，真真假假，我究竟变成了谁？",
//                "http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/11/2017-11-03_37-744135043-750x420.jpg",
//                "http://cdnxdc.tanzi88.com/XDC/dvideo/2017/11/29/7c21c43ba0817742ff0224e9bcdf12b6.mp4"));
//
//        return videoList;
//    }

    public static List<VideoBean> getVideoList() {
        List<VideoBean> videoList = new ArrayList<>();
        videoList.add(new VideoBean("【TED】探索世界最深洞穴",
                "https://nimg.ws.126.net/?url=http://open-image.ws.126.net/image/snapshot_movie/2020/2/c/b/31e932b622784a47935c6de0def057cb.jpg&thumbnail=270x150&quality=95&type=jpg",
                "https://mov.bn.netease.com/open-movie/nos/mp4/2014/05/28/S9SDQS50P_sd.mp4"));

        videoList.add(new VideoBean("【TEDx】做一个像样的人，不难",
                "https://nimg.ws.126.net/?url=http://open-image.ws.126.net/image/snapshot_movie/2017/6/a/9/271e1f1d719644aaa50125bf156754a9.jpg&thumbnail=270x150&quality=95&type=jpg",
                "https://mov.bn.netease.com/open-movie/nos/mp4/2017/06/13/SCLSCU6AF_sd.mp4"));

        videoList.add(new VideoBean("【TED】动画如何帮助科学家检验假说",
                "https://nimg.ws.126.net/?url=http://open-image.ws.126.net/image/snapshot_movie/2016/11/0/5/98619f1ba84840c3993d1169f3ef5b05.jpg&thumbnail=270x150&quality=95&type=jpg",
                "https://mov.bn.netease.com/open-movie/nos/mp4/2015/01/26/SAFVOMNC2_sd.mp4"));

        videoList.add(new VideoBean("【TED】为什么智能统计数据是打击犯罪的关键",
                "https://nimg.ws.126.net/?url=http://open-image.ws.126.net/image/snapshot_movie/2016/11/a/5/162f906e7d63481992c306cd8e0f35a5.jpg&thumbnail=270x150&quality=95&type=jpg",
                "https://mov.bn.netease.com/open-movie/nos/mp4/2014/05/28/S9SDU7P71_sd.mp4"));

        videoList.add(new VideoBean("【TED】Jinsop Lee: 五感设计",
                "https://nimg.ws.126.net/?url=http://open-image.ws.126.net/image/snapshot_movie/2016/11/8/9/7c555c0012964c3f9abb1929d175d389.jpg&thumbnail=270x150&quality=95&type=jpg",
                "https://mov.bn.netease.com/open-movie/nos/mp4/2013/11/01/S9BLS7OIL_sd.mp4"));

        videoList.add(new VideoBean("【TED】达顿解释达尔文主义的美的理论",
                "https://nimg.ws.126.net/?url=http://open-image.ws.126.net/image/snapshot_movie/2016/11/9/4/7eb4f30ba6904b1a9158b554dc2e9694.jpg&thumbnail=270x150&quality=95&type=jpg",
                "https://mov.bn.netease.com/open-movie/nos/mp4/2013/10/18/S9AHLTF76_sd.mp4"));

        videoList.add(new VideoBean("【TED】破解语言学习之谜",
                "https://nimg.ws.126.net/?url=http://open-image.ws.126.net/image/snapshot_movie/2016/11/3/2/eaa0e1e0dfb34a5da7ad81adf46f0732.jpg&thumbnail=270x150&quality=95&type=jpg",
                "https://mov.bn.netease.com/open-movie/nos/mp4/2015/08/07/SAVFT8DKT_sd.mp4"));

        videoList.add(new VideoBean("【TED】被判无期徒刑的女人: 一首来自监狱中无期徒刑的女人的歌",
                "https://nimg.ws.126.net/?url=http://open-image.ws.126.net/image/snapshot_movie/2016/11/8/2/94a74953133f49d586cb514098e85782.jpg&thumbnail=270x150&quality=95&type=jpg",
                "https://mov.bn.netease.com/open-movie/nos/mp4/2015/08/11/SAVQN530E_sd.mp4"));

        videoList.add(new VideoBean("【TED】网络暴力的失控",
                "https://nimg.ws.126.net/?url=http://open-image.ws.126.net/image/snapshot_movie/2016/11/d/8/c0c6c84af9d246d8848d209f4bb333d8.jpg&thumbnail=270x150&quality=95&type=jpg",
                "https://mov.bn.netease.com/open-movie/nos/mp4/2015/09/16/SB2N4R35N_sd.mp4"));

        videoList.add(new VideoBean("【TED】未来医疗的无线化",
                "https://nimg.ws.126.net/?url=http://open-image.ws.126.net/image/snapshot_movie/2016/11/4/d/172a5e409c4d474c924fc32f16ea0f4d.jpg&thumbnail=270x150&quality=95&type=jpg",
                "https://mov.bn.netease.com/open-movie/nos/mp4/2013/06/17/S90LHADVU_sd.mp4"));

//        videoList.add(new VideoBean("预告片11",
//                "https://cms-bucket.nosdn.127.net/eb411c2810f04ffa8aaafc42052b233820180418095416.jpeg",
//                "http://vfx.mtime.cn/Video/2019/03/14/mp4/190314102306987969.mp4"));
//
//        videoList.add(new VideoBean("预告片12",
//                "https://cms-bucket.nosdn.127.net/cb37178af1584c1588f4a01e5ecf323120180418133127.jpeg",
//                "http://vfx.mtime.cn/Video/2019/03/13/mp4/190313094901111138.mp4"));
//
//        videoList.add(new VideoBean("预告片13",
//                "https://cms-bucket.nosdn.127.net/eb411c2810f04ffa8aaafc42052b233820180418095416.jpeg",
//                "http://vfx.mtime.cn/Video/2019/03/12/mp4/190312143927981075.mp4"));
//
//        videoList.add(new VideoBean("预告片14",
//                "https://cms-bucket.nosdn.127.net/cb37178af1584c1588f4a01e5ecf323120180418133127.jpeg",
//                "http://vfx.mtime.cn/Video/2019/03/12/mp4/190312083533415853.mp4"));

        return videoList;
    }

//    /**
//     * 抖音演示数据
//     */
//    public static List<VideoBean> getTikTokVideoList() {
//        List<VideoBean> videoList = new ArrayList<>();
//        videoList.add(new VideoBean("",
//                "https://p9.pstatp.com/large/4c87000639ab0f21c285.jpeg",
//                "https://aweme.snssdk.com/aweme/v1/play/?video_id=97022dc18711411ead17e8dcb75bccd2&line=0&ratio=720p&media_type=4&vr_type=0"));
//
//        videoList.add(new VideoBean("",
//                "https://p1.pstatp.com/large/4bea0014e31708ecb03e.jpeg",
//                "https://aweme.snssdk.com/aweme/v1/play/?video_id=374e166692ee4ebfae030ceae117a9d0&line=0&ratio=720p&media_type=4&vr_type=0"));
//
//        videoList.add(new VideoBean("",
//                "https://p1.pstatp.com/large/4bb500130248a3bcdad0.jpeg",
//                "https://aweme.snssdk.com/aweme/v1/play/?video_id=8a55161f84cb4b6aab70cf9e84810ad2&line=0&ratio=720p&media_type=4&vr_type=0"));
//
//        videoList.add(new VideoBean("",
//                "https://p9.pstatp.com/large/4b8300007d1906573584.jpeg",
//                "https://aweme.snssdk.com/aweme/v1/play/?video_id=47a9d69fe7d94280a59e639f39e4b8f4&line=0&ratio=720p&media_type=4&vr_type=0"));
//
//        videoList.add(new VideoBean("",
//                "https://p9.pstatp.com/large/4b61000b6a4187626dda.jpeg",
//                "https://aweme.snssdk.com/aweme/v1/play/?video_id=3fdb4876a7f34bad8fa957db4b5ed159&line=0&ratio=720p&media_type=4&vr_type=0"));
//
//        videoList.add(new VideoBean("",
//                "https://p9.pstatp.com/large/4c87000639ab0f21c285.jpeg",
//                "https://aweme.snssdk.com/aweme/v1/play/?video_id=97022dc18711411ead17e8dcb75bccd2&line=0&ratio=720p&media_type=4&vr_type=0"));
//
//        videoList.add(new VideoBean("",
//                "https://p1.pstatp.com/large/4bea0014e31708ecb03e.jpeg",
//                "https://aweme.snssdk.com/aweme/v1/play/?video_id=374e166692ee4ebfae030ceae117a9d0&line=0&ratio=720p&media_type=4&vr_type=0"));
//
//        videoList.add(new VideoBean("",
//                "https://p1.pstatp.com/large/4bb500130248a3bcdad0.jpeg",
//                "https://aweme.snssdk.com/aweme/v1/play/?video_id=8a55161f84cb4b6aab70cf9e84810ad2&line=0&ratio=720p&media_type=4&vr_type=0"));
//
//        videoList.add(new VideoBean("",
//                "https://p9.pstatp.com/large/4b8300007d1906573584.jpeg",
//                "https://aweme.snssdk.com/aweme/v1/play/?video_id=47a9d69fe7d94280a59e639f39e4b8f4&line=0&ratio=720p&media_type=4&vr_type=0"));
//
//        videoList.add(new VideoBean("",
//                "https://p9.pstatp.com/large/4b61000b6a4187626dda.jpeg",
//                "https://aweme.snssdk.com/aweme/v1/play/?video_id=3fdb4876a7f34bad8fa957db4b5ed159&line=0&ratio=720p&media_type=4&vr_type=0"));
//
//        videoList.add(new VideoBean("",
//                "https://p9.pstatp.com/large/4c87000639ab0f21c285.jpeg",
//                "https://aweme.snssdk.com/aweme/v1/play/?video_id=97022dc18711411ead17e8dcb75bccd2&line=0&ratio=720p&media_type=4&vr_type=0"));
//
//        videoList.add(new VideoBean("",
//                "https://p1.pstatp.com/large/4bea0014e31708ecb03e.jpeg",
//                "https://aweme.snssdk.com/aweme/v1/play/?video_id=374e166692ee4ebfae030ceae117a9d0&line=0&ratio=720p&media_type=4&vr_type=0"));
//
//        videoList.add(new VideoBean("",
//                "https://p1.pstatp.com/large/4bb500130248a3bcdad0.jpeg",
//                "https://aweme.snssdk.com/aweme/v1/play/?video_id=8a55161f84cb4b6aab70cf9e84810ad2&line=0&ratio=720p&media_type=4&vr_type=0"));
//
//        videoList.add(new VideoBean("",
//                "https://p9.pstatp.com/large/4b8300007d1906573584.jpeg",
//                "https://aweme.snssdk.com/aweme/v1/play/?video_id=47a9d69fe7d94280a59e639f39e4b8f4&line=0&ratio=720p&media_type=4&vr_type=0"));
//
//        videoList.add(new VideoBean("",
//                "https://p9.pstatp.com/large/4b61000b6a4187626dda.jpeg",
//                "https://aweme.snssdk.com/aweme/v1/play/?video_id=3fdb4876a7f34bad8fa957db4b5ed159&line=0&ratio=720p&media_type=4&vr_type=0"));
//        return videoList;
//    }

    public static List<TiktokBean> tiktokData;

    public static List<TiktokBean> getTiktokDataFromAssets(Context context) {
        try {
            if (tiktokData == null) {
                InputStream is = context.getAssets().open("tiktok_data");
                int length = is.available();
                byte[] buffer = new byte[length];
                is.read(buffer);
                is.close();
                String result = new String(buffer, Charset.forName("UTF-8"));
                tiktokData = TiktokBean.arrayTiktokBeanFromData(result);
            }
            return tiktokData;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
