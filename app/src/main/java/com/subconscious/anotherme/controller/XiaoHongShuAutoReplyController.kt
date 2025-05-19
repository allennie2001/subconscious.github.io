package com.subconscious.anotherme.controller

import android.accessibilityservice.AccessibilityService
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import androidx.annotation.RequiresApi
import com.subconscious.anotherme.util.ClickUtil
import com.subconscious.anotherme.util.ClipboardUtil
import com.subconscious.anotherme.util.ScreenUtil
import com.subconscious.anotherme.util.TraversalUtil


class XiaoHongShuAutoReplyController(var service: AccessibilityService, var curPackage: String) :
    BaseController() {

    var curPage: String? = null
    var curSearchContent: String? = null
    var searchDataList: MutableList<String> = mutableListOf()

    var isBackFromTalentPage: Boolean = false

    val hander: Handler = Handler(Looper.getMainLooper())

    init {
        searchDataList.add("山城小栗旬的理发日记");
        searchDataList.add("葡萄大人");
        searchDataList.add("沈辞er.");
        searchDataList.add("白昼小熊");
        searchDataList.add("鲑鱼子");
        searchDataList.add("icycream");
        searchDataList.add("Sinoe泷吟");
        searchDataList.add("绒耳朵儿");
        searchDataList.add("是你阿季呀");
        searchDataList.add("天总");
        searchDataList.add("区区欧阳 ohyx-");
        searchDataList.add("Asu丶艾斯");
        searchDataList.add("谷梨梨");
        searchDataList.add("爱拍照的玉老师");
        searchDataList.add("夙胤言生");
        searchDataList.add("是西西吖");
        searchDataList.add("陈都灵");
        searchDataList.add("黄饱饱了");
        searchDataList.add("BettySays");
        searchDataList.add("一条辣cc");
        searchDataList.add("刘梦LapMoby");
        searchDataList.add("小骨啊骨");
        searchDataList.add("景甜");
        searchDataList.add("朱珠ZhuZhu");
        searchDataList.add("陆鹫贰黑");
        searchDataList.add("青日子子");
        searchDataList.add("王悦伊");
        searchDataList.add("Clarice的硕士妈咪");
        searchDataList.add("chilly香菜");
        searchDataList.add("在下悟空是也");
        searchDataList.add("阿靖靖靖");
        searchDataList.add("彼岸的水坑");
        searchDataList.add("全智鹅");
        searchDataList.add("140斤的芋头");
        searchDataList.add("腻腻ninii");
        searchDataList.add("独角兽的粉粉");
        searchDataList.add("乔欣Bridgette");
        searchDataList.add("六一不是猫");
        searchDataList.add("丁儿丁儿");
        searchDataList.add("方枪枪Monica");
        searchDataList.add("Babeei张张");
        searchDataList.add("Aikoko_");
        searchDataList.add("油炸QQ糖");
        searchDataList.add("青羽Vic");
        searchDataList.add("仙女软本人");
        searchDataList.add("桃瑞思");
        searchDataList.add("JZ");
        searchDataList.add("只小萌");
        searchDataList.add("叶芳伶link");
        searchDataList.add("Kilory_利利");
        searchDataList.add("满月岁岁");
        searchDataList.add("白未ALI");
        searchDataList.add("bibibiu");
        searchDataList.add("POSHEPOSE高定如心");
        searchDataList.add("李马特");
        searchDataList.add("爆胎草莓粥");
        searchDataList.add("张元气");
        searchDataList.add("零青子");
        searchDataList.add("其寺Mar");
        searchDataList.add("FortyLions");
        searchDataList.add("钢铁猛懒");
        searchDataList.add("Tina面料测评");
        searchDataList.add("张琪er");
        searchDataList.add("Taoyii桃一");
        searchDataList.add("快把柚子带走");
        searchDataList.add("范00");
        searchDataList.add("主播潘小蓉");
        searchDataList.add("大美牛");
        searchDataList.add("南北芝麻糊");
        searchDataList.add("冬夏&安柒❤");
        searchDataList.add("陶四七-");
        searchDataList.add("钱棒棒");
        searchDataList.add("阿葛梨梨");
        searchDataList.add("妤Yuki");
        searchDataList.add("汪澜的妹妹");
        searchDataList.add("小野智恩");
        searchDataList.add("暖傲男");
        searchDataList.add("未岚RAN");
        searchDataList.add("泽光君");
        searchDataList.add("大锤妹妹");
        searchDataList.add("帆帆吃饱了");
        searchDataList.add("苏苏_Sss");
        searchDataList.add("启豪Kaiho");
        searchDataList.add("我是球球菌");
        searchDataList.add("甩饼大嫂");
        searchDataList.add("阿毓yuyu");
        searchDataList.add("my-name-is-MM");
        searchDataList.add("炸鸡欧尼Oni");
        searchDataList.add("姣姣来了");
        searchDataList.add("一只希贝-");
        searchDataList.add("曾鹿儿");
        searchDataList.add("开会的大会");
        searchDataList.add("小鬼大大酱");
        searchDataList.add("安吉林Angelene");
        searchDataList.add("ChiliCharlotte");
        searchDataList.add("风华绝代的石榴姐");
        searchDataList.add("E吖E小姐");
        searchDataList.add("我是袁一琦");
        searchDataList.add("Agent辰辰");
        searchDataList.add("彭彭不吃饭");
        searchDataList.add("杨意子_");
        searchDataList.add("是Nini啊--");
        searchDataList.add("漫妮Fiona");
        searchDataList.add("林诗琦NICKI");
        searchDataList.add("Khaless3");
        searchDataList.add("夜忙症");
        searchDataList.add("曲七");
        searchDataList.add("bulunano（备婚版");
        searchDataList.add("乐仔Levi");
        searchDataList.add("全全");
        searchDataList.add("钟晨瑶ZCY");
        searchDataList.add("婉婉酱");
        searchDataList.add("豆豆本豆");
        searchDataList.add("关大宝");
        searchDataList.add("托托Toto");
        searchDataList.add("刘大大");
        searchDataList.add("小怡同学");
        searchDataList.add("其恩酱");
        searchDataList.add("Darrrrcy");
        searchDataList.add("李施嬅 Selena");
        searchDataList.add("王秀竹");
        searchDataList.add("Savislook");
        searchDataList.add("油菜花花");
        searchDataList.add("阿雅");
        searchDataList.add("六金yE");
        searchDataList.add("黑娃呦～");
        searchDataList.add("Luna教主");
        searchDataList.add("小甜椒Rukia");
        searchDataList.add("苏半月");
        searchDataList.add("梁夹心");
        searchDataList.add("大大木和小小嘟");
        searchDataList.add("一菲iffy");
        searchDataList.add("三加一");
        searchDataList.add("阿紫");
        searchDataList.add("120斤的欣怡");
        searchDataList.add("陳山聰");
        searchDataList.add("挖宝女孩");
        searchDataList.add("空贝贝贝贝贝");
        searchDataList.add("JNwannnng");
        searchDataList.add("辣椒精儿");
        searchDataList.add("NexT1DE-胡烨韬");
        searchDataList.add("郁可唯");
        searchDataList.add("依涵");
        searchDataList.add("Winnie Tang");
        searchDataList.add("是公子陈呀");
        searchDataList.add("-谢安然-");
        searchDataList.add("脑洞少女T");
        searchDataList.add("鸭学长开课啦");
        searchDataList.add("Little Nothing");
        searchDataList.add("项炸炸");
        searchDataList.add("文仔Deemo");
        searchDataList.add("SOURCE");
        searchDataList.add("熊夫人");
        searchDataList.add("你的年年");
        searchDataList.add("辣椒炒睿");
        searchDataList.add("丸子Tina");
        searchDataList.add("熊猫叮");
        searchDataList.add("黎篱");
        searchDataList.add("小岛大瑀");
        searchDataList.add("范玮琪");
        searchDataList.add("Elena朴");
        searchDataList.add("张阿星-");
        searchDataList.add("AyanWang");
        searchDataList.add("Skiko小希子");
        searchDataList.add("hello我是沱沱");
        searchDataList.add("吕燕");
        searchDataList.add("彭王者是小洋");
        searchDataList.add("handsmood");
        searchDataList.add("李李奉");
        searchDataList.add("吕颖Ivy");
        searchDataList.add("宫沐泽");
        searchDataList.add("憨桃人儿");
        searchDataList.add("林子熙");
        searchDataList.add("一粒维c");
        searchDataList.add("刘小被儿不是盖的");
        searchDataList.add("画画-");
        searchDataList.add("Akiiiko");
        searchDataList.add("小山菜奈");
        searchDataList.add("小小小小白");
        searchDataList.add("陈若仪");
        searchDataList.add("胡艺馨");
        searchDataList.add("黄婷婷");
        searchDataList.add("周洁琼");
        searchDataList.add("ssserena hu");
        searchDataList.add("陈大事");
        searchDataList.add("你是一个果子");
        searchDataList.add("陈奕伊chen11");
        searchDataList.add("山南以北");
        searchDataList.add("徐大脸吖");
        searchDataList.add("赵昭仪");
        searchDataList.add("MissCharming");
        searchDataList.add("短头花");
        searchDataList.add("y=ax+b");
        searchDataList.add("大果大果赵大果");
        searchDataList.add("JoJo酱");
        searchDataList.add("李李念禾");
        searchDataList.add("卢佳卢小佳");
        searchDataList.add("小小莎老师");
        searchDataList.add("是花总吖");
        searchDataList.add("咸野");
        searchDataList.add("何宣林");
        searchDataList.add("俞香子");
        searchDataList.add("Imzsy12");
        searchDataList.add("当然是当");
        searchDataList.add("Yi_大头");
        searchDataList.add("其斤小小");
        searchDataList.add("苦苦小羊");
        searchDataList.add("香菱鼓瑟");
        searchDataList.add("桃子小姐Vincci");
        searchDataList.add("单眼皮王公子");
        searchDataList.add("大洪洪");
        searchDataList.add("一井豚");
        searchDataList.add("林小宅");
        searchDataList.add("莉贝琳");
        searchDataList.add("Yuki雪雪");
        searchDataList.add("陈醉醉");
        searchDataList.add("洪潇Hanna");
        searchDataList.add("沙沙sasa");
        searchDataList.add("99u");
        searchDataList.add("老敏哥");
        searchDataList.add("怡含怡含");
        searchDataList.add("贝小小七");
        searchDataList.add("sssslin");
        searchDataList.add("抠搜 ya ya 爱挖宝");
        searchDataList.add("Sherman-closet");
        searchDataList.add("仓大佬");
        searchDataList.add("泥美");
        searchDataList.add("Yuri睿哥");
        searchDataList.add("东瓜嘀嘀");
        searchDataList.add("小梦李孟羲");
        searchDataList.add("林堅定_");
        searchDataList.add("彭杨");
        searchDataList.add("七颗_");
        searchDataList.add("阿King哥");
        searchDataList.add("牙膏Soda");
        searchDataList.add("球球你了");
        searchDataList.add("刘芸");
        searchDataList.add("吴敌！");
        searchDataList.add("郭晓敏");
        searchDataList.add("saisai");
        searchDataList.add("王塔塔");
        searchDataList.add("松青学姐");
        searchDataList.add("陈一丁Dingslook");
        searchDataList.add("-Lenayan");
        searchDataList.add("Milly米粒");
        searchDataList.add("笑笑同学啊");
        searchDataList.add("迪拜奶奶");
        searchDataList.add("Nancy今天早睡了吗");
        searchDataList.add("PD大人");
        searchDataList.add("aboutyee");
        searchDataList.add("芒果草莓干");
        searchDataList.add("刘一口");
        searchDataList.add("壹五五");
        searchDataList.add("美洋MEIYANG");
        searchDataList.add("零零要早睡");
        searchDataList.add("康康和爷爷");
        searchDataList.add("木子卓儿");
        searchDataList.add("王一浩同学");
        searchDataList.add("萧潇XiaoXiao");
        searchDataList.add("王乙孑川");
        searchDataList.add("曹斐然");
        searchDataList.add("郭十十呢");
        searchDataList.add("陈姝君");
        searchDataList.add("雨七七");
        searchDataList.add("小钰");
        searchDataList.add("陈思如");
        searchDataList.add("AnotherOlivia");
        searchDataList.add("石大小姐");
        searchDataList.add("大码胖佳佳");
        searchDataList.add("仲qiuqiu");
        searchDataList.add("是圆脸考拉");
        searchDataList.add("小肥呼呼");
        searchDataList.add("因为我是tyt");
        searchDataList.add("田原");
        searchDataList.add("吴佳烨");
        searchDataList.add("曹琳琳");
        searchDataList.add("吴芊盈");
        searchDataList.add("姜京佐");
        searchDataList.add("ice艾晓琪");
        searchDataList.add("吴糖白桃茶");
        searchDataList.add("卢杉");
        searchDataList.add("伊伊CHENE YI");
        searchDataList.add("啊川");
        searchDataList.add("胖梨阿姨");
        searchDataList.add("薛凱琪");
        searchDataList.add("痞幼");
        searchDataList.add("李斯羽");
        searchDataList.add("丁禹兮");
        searchDataList.add("VeryDee");
        searchDataList.add("哪里哪里吖");
        searchDataList.add("Mini大悦");
        searchDataList.add("陈小纭");
        searchDataList.add("比鲁斯.雯");
        searchDataList.add("王漪淼");
        searchDataList.add("赵大喜");
        searchDataList.add("车晓");
        searchDataList.add("三木三木呢");
        searchDataList.add("北小北-珍氏珠宝");
        searchDataList.add("曾一萱");
        searchDataList.add("迪小小");
        searchDataList.add("叶是");
        searchDataList.add("刘知宜BananaFashion");
        searchDataList.add("谢可寅");
        searchDataList.add("子回頭是浪");
        searchDataList.add("杨云绚");
        searchDataList.add("演员林源");
        searchDataList.add("贺聪HeCong");
        searchDataList.add("小镜静_");
        searchDataList.add("芝芝早安_");
        searchDataList.add("许馨文");
        searchDataList.add("简单手工");
        searchDataList.add("万小七");
        searchDataList.add("阿月月ayue");
        searchDataList.add("瘦宅下饭菜");
        searchDataList.add("井柏然");
        searchDataList.add("学妹娜娜");
        searchDataList.add("苓妹妹");
        searchDataList.add("谢小雨amy");
        searchDataList.add("米儿姐姐");
        searchDataList.add("王子文");
        searchDataList.add("皮皮夏的评测日记");
        searchDataList.add("Alfie 的Angel");
        searchDataList.add("宁静");
        searchDataList.add("悦恩oni");
        searchDataList.add("ColaCat噗");
        searchDataList.add("AndreaAndJayden");
        searchDataList.add("卡琳娜和揉西");
        searchDataList.add("MAXINE时尚日记");
        searchDataList.add("痣女如花");
        searchDataList.add("勇敢肉肉");
        searchDataList.add("大黄h");
        searchDataList.add("上优来啦");
        searchDataList.add("三吴学姐");
        searchDataList.add("优子姐姐");
        searchDataList.add("饭饭又饿了");
        searchDataList.add("余科目美食");
        searchDataList.add("团子姐妹");
        searchDataList.add("任小艺_");
        searchDataList.add("阿吴");
        searchDataList.add("Ann大爷");
        searchDataList.add("萌儿youm");
        searchDataList.add("若瑜欧尼呀");
        searchDataList.add("抽抽子的日常");
        searchDataList.add("lu.meng");
        searchDataList.add("Cecelia");
        searchDataList.add("小谭需要很努力");
        searchDataList.add("瑶三岁dazzling");
        searchDataList.add("米兰达 可儿");
        searchDataList.add("ReykjavikkK");
        searchDataList.add("韦韦韦欣言");
        searchDataList.add("aaaaxbbb");
        searchDataList.add("一七");
        searchDataList.add("陈子蜜");
        searchDataList.add("小川【美食测评】");
        searchDataList.add("露露要飞");
        searchDataList.add("未未weiwei");
        searchDataList.add("Lelush 利路修");
        searchDataList.add("周子清");
        searchDataList.add("大玲玲");
        searchDataList.add("AmandaX");
        searchDataList.add("小来");
        searchDataList.add("Chris姐姐_");
        searchDataList.add("王玉萌");
        searchDataList.add("pp桃子");
        searchDataList.add("张饱饱zzz");
        searchDataList.add("肉脸喵喵");
        searchDataList.add("Kim Kardashian");
        searchDataList.add("厉害的小红");
        searchDataList.add("文司月");
        searchDataList.add("演员王子奇");
        searchDataList.add("Lee权哲");
        searchDataList.add("李菲儿love");
        searchDataList.add("演员马可");
        searchDataList.add("刘惜君");
        searchDataList.add("黄现喜Suzi de Givenchy");
        searchDataList.add("刘芷微");
        searchDataList.add("Nene郑乃馨");
        searchDataList.add("有梦想的梦瑶");
        searchDataList.add("用户已注销");
        searchDataList.add("老弟学穿搭");
        searchDataList.add("姬天语 PeggyJi");
        searchDataList.add("王可如Nina");
        searchDataList.add("刘耀元");
        searchDataList.add("钟金琪");
        searchDataList.add("朱松玮Ray");
        searchDataList.add("松本惠奈");
        searchDataList.add("映玲");
        searchDataList.add("连凯 andrew");
        searchDataList.add("小虎牙欢欢");
        searchDataList.add("袁冰妍");
        searchDataList.add("张咸-Sa1t");
        searchDataList.add("溫心");
        searchDataList.add("李昕岳Sienna");
        searchDataList.add("嘉容Christine");
        searchDataList.add("马藜");
        searchDataList.add("陈子由");
        searchDataList.add("吴涟序Olivia");
        searchDataList.add("Taylor Hill");
        searchDataList.add("张雨甜甜");
        searchDataList.add("路晨");
        searchDataList.add("陆柯燃");
        searchDataList.add("孙一杰");
        searchDataList.add("彭必瑶");
        searchDataList.add("辛芷蕾");
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        super.onAccessibilityEvent(event)
        when (event!!.eventType) {
            AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED -> {
                // 获取当前的包名和类名
                val packageName = event.packageName.toString()
                val className = event.className.toString()


                // 判断是否是新的页面
                if (curPackage.equals(packageName) || !curPage.equals(className)
                ) {
                    // 页面发生变化，可以认为是跳转
                    Log.d("zunyu", "New Page: $className")
                    // 更新当前页面信息
                    curPage = className
                    checkPage(event)
                }
            }

            AccessibilityEvent.TYPE_VIEW_FOCUSED -> {
                if (event.source != null && searchDataList.size > 0) {
                    val bundle = Bundle()
                    bundle.putCharSequence(
                        AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE,
                        searchDataList[0]
                    )
                    event.source!!.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, bundle)
                }

                if (isBackFromTalentPage) {
                    ClickUtil.performActionClickByText("全部删除", service)
                }
            }

            AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED -> {
                if (event.source != null) {
                    val text = event.source!!.text.toString()
                    if (text.startsWith("搜索, ")) {
                        curSearchContent = text.removeRange(0, 4)
                    } else {
                        curSearchContent = text
                    }
                }
                val searchListNode = TraversalUtil.findNodeByDeepTraversal(
                    service.rootInActiveWindow,
                    "androidx.recyclerview.widget.RecyclerView"
                )
                var baseSearchLog: String? = null
                if (curPage.equals("com.xingin.alioth.search.GlobalSearchActivity")) {
                    baseSearchLog = "默认搜索推荐"
                } else {
                    baseSearchLog = "搜索{${searchDataList.get(0)}提示词}"
                }
                Log.d("zunyu", "-----------${baseSearchLog}：爬虫开始-------------")
                searchListNode?.let { TraversalUtil.logTextInChildren(it) }
                Log.d("zunyu", "-----------${baseSearchLog}：爬虫结束-------------")


                if (isBackFromTalentPage) {
                    isBackFromTalentPage = false
                } else {
                    ClickUtil.performActionClickByText("搜索", service)
                }

            }

            AccessibilityEvent.TYPE_VIEW_SCROLLED -> {
                if (isBackFromTalentPage) {
                    ClickUtil.performActionClickByText("全部删除", service)
                    return
                }
                val avatarLayoutList =
                    service.rootInActiveWindow.findAccessibilityNodeInfosByViewId("com.xingin.xhs:id/avatarLayout")
                if (avatarLayoutList != null && avatarLayoutList.size > 0) {
                    val avatar = avatarLayoutList[0]
                    if (avatar.isClickable) {
                        ClickUtil.pointClick(avatar, service)
                    }
                }
            }
        }
    }


    private fun checkPage(event: AccessibilityEvent?) {
        when (curPage) {
            // 首页
            "com.xingin.xhs.index.v2.IndexActivityV2" -> {
                ClickUtil.performActionClickByText("搜索", service)
            }

            "com.xingin.matrix.v2.profile.newpage.NewOtherUserActivity" -> {
                Log.d("zunyu", "-----------个人主页：爬虫开始-------------")
                TraversalUtil.logTextInChildren(service.rootInActiveWindow)
                Log.d("zunyu", "-----------个人主页：爬虫结束-------------")

                hander.postDelayed({
                    var viewpager = TraversalUtil.findNodeByDeepTraversal(
                        service.rootInActiveWindow,
                        "androidx.viewpager.widget.ViewPager"
                    )
                    var recyclerView = viewpager?.getChild(0)?.getChild(0)?.getChild(0)?.getChild(0)
                    var firstNote = recyclerView?.getChild(0)
                    firstNote?.let { ClickUtil.pointClick(it, service) }
                }, 1000)

            }

            "com.xingin.alioth.search.GlobalSearchActivity" -> {

            }

            "com.xingin.matrix.detail.activity.DetailFeedActivity" -> {
                var commentLayout =
                    service.rootInActiveWindow.findAccessibilityNodeInfosByViewId("com.xingin.xhs:id/commentLayout")
                if (!commentLayout.isNullOrEmpty()) {
                    commentLayout[0]?.let { ClickUtil.pointClick(commentLayout[0], service) }
                }
            }
        }
    }

    private fun returnLastPage() {
        if (searchDataList.size > 0) {
            searchDataList.removeAt(0)
            isBackFromTalentPage = true
            hander.postDelayed(
                { ClickUtil.performActionClickByText("返回", service) },
                500
            )
        }
    }
}