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


class XiaoHongShuSearchUserController(var service: AccessibilityService, var curPackage: String) :
    BaseController() {

    var curPage: String? = null
    var curSearchContent: String? = null
    var searchDataList: MutableList<String> = mutableListOf(
//        "李子李",
//        "胡米米的随手一做",
//        "赤井牙美",
//        "于雯_",
//        "挖宝女孩",
//        "痣女如花",
//        "玛丽玛丽轰",
//        "九点奶思",
//        "夹子大哥",
//        "苏半月",
//        "阿lin拯救花",
//        "派大馨",
//        "FionaRemake",
//        "席莎莎-loooxc",
//        "AnotherMan中文版",
//        "T中文版",
//        "嗨西西亚",
//        "卡卡时尚笔记",
//        "时尚池小梨",
//        "马锐",
//        "聪姐VTG",
//        "刘雯",
//        "兰花干子",
//        "策别",
//        "DANA进步中",
//        "郭郭旭旭",
//        "刘虞佳",
//        "DuoLipa",
//        "世界麦克风",
//        "小宇小宇yola",
//        "野生小枣",
//        "拎出苒",
//        "杨嘉子琪",
//        "砂拉越Sarawak",
//        "阿拉蕾爱喝酸奶",
//        "Yun你芸",
//        "是Jade吖🍀",
//        "Vessel.vv",
//        "黑珍珠Dani",
//        "仙女WaNg",
//        "银角大王",
//        "小田切尼",
//        "快活吴",
//        "-上下名-",
//        "不吃鸡蛋",
//        "五人病",
//        "阿诺和大力",
//        "粥粥yee",
//        "🔺Artem🔻",
//        "阿文就是Aya",
//        "VChina",
//        "WFirst",
//        "VOGUE服饰与美容",
//        "张北冥",
//        "十一列车-",
//        "曹甚麼",
//        "bububalu",
//        "QuickClick",
        "錦財本財凱特陳",
        "W中文版",
        "格东",
        "圆圆三文鱼🍣",
        "valeriia_park",
        "Daniel Millar",
        "Ralph Leng",
        "Atanga Twins",
        "时尚先生Esquire",
        "Alex Schlab",
        "Moritz Hau",
        "芭蕾王子Julian",
        "Jarvis Aivali",
        "垫底辣孩",
        "是小柑橘子",
        "是虚枝儿",
        "小粽子Selina",
        "尤那沙",
        "李丹妮",
        "无所不能的方凯瑞",
        "胡兵",
        "读书人小戴",
        "40",
        "陈碧舸Bonnie",
        "嘉人",
        "pangdingbb",
        "妮妮这样子",
        "华娟Judyhua",
        "MixWei",
        "1vvvLucy",
        "知识薯",
        "罗那那💕",
        "任晴佳Ella",
        "李存在",
        "烤鱼片儿",
        "Hadwin.",
        "HiyaAva",
        "demna",
        "莫非万里",
        "图仔",
        "是盒仔",
        "小蟹壳儿",
        "屁琪",
        "知世",
        "可爱又迷人的反派角色💃",
        "周戴王",
        "万大娃子",
        "Delfino雕雕",
        "诸葛苏佳",
        "晓雪",
        "Rocco刘冲",
        "喜欢被拍的摄影小狮子",
        "辛区零",
        "Imzsy12",
        "大野丽莎",
        "黄现喜Suzi de Givenchy",
        "阿蛋黄靖",
        "食肉小羊",
        "Miki _yuanjz",
        "大鹅",
        "HeyMila",
        "penghunana",
        "葡萄大人🍇",
        "橙汁橙",
        "Lili",
        "Tse蟹蟹",
        "肚子小一点",
        "slowlywong",
        "肉脸kk",
        "KaridaMiller",
        "路易艾斯",
        "W51-",
        "內有惡犬出沒",
        "是阿月鸭",
        "Baby G",
        "买薯条",
        "糙糙Caocao",
        "自核",
        "月亮明明",
        "王甜甜",
        "欢谢",
        "DeenW",
        "六深",
        "CircleCircle",
        "琚琚琚橘子",
        "指南针的磁针",
        "虎崽_huu",
        "我是饭团",
        "小米鹅",
        "一零",
        "kt",
        "我可太冲动了",
        "六一不是猫",
        "盈莹",
        "Chelsea鹅",
        "Jessicadu2.0",
        "十八闲客户",
        "Syuta",
        "Guoruru",
        "雅雅雅哥",
        "不二颜",
        "经曼曼曼莉噎",
        "小卷伶子",
        "小山菜奈",
        "杨娃娃",
        "Ran.W",
        "克克Keke",
        "爆炸头大柠檬",
        "Kat🐱",
        "思远",
        "兜里有糖",
        "RNA结构少女",
        "1saye",
        "一口日记",
        "Gocherry_",
        "chowei",
        "Aukse Grei",
        "Midori Lau.",
        "巧克鹿",
        "小野六缘",
        "聂小倩她老板",
        "辣辣来撸妆",
        "赤仁赖",
        "初九",
        "李小花爱睡觉",
        "87-",
        "Abigail曼",
        "julia_ty",
        "Jy小語",
        "野生麒麟",
        "金柏莉",
        "yevtopyer",
        "背带裤米菲兔🐰",
        "巴黎三区高圆圆",
        "Emilie-Ye",
        "Paola Cossentino",
        "leanneansar",
        "人人RENREN",
        "vicvicxia",
        "野原鼠黍",
        "法式美学研究室",
        "时尚乖",
        "明星同款",
        "肆己SIJI",
        "This Was Cool",
        "凯凯",
        "安安崽An",
        "邦尼邦尼",
        "烤地瓜仙子",
        "NANA是我吖",
        "一只鱼而已",
        "没觉",
        "吉吉儿",
        "小树芽子",
        "7yokakinn",
        "YANG咩咩",
        "EYESTORE",
        "钟楚曦",
        "iDoubleS时髦说",
        "时尚帆船CLUB",
        "Ming奚梦瑶",
        "Twice-Chic",
        "春楠",
        "LANYI礼服事务所",
        "千惠在这里",
        "费斯FACE",
        "费加罗FIGARO",
        "项炸炸",
        "时髦哔哔叽",
        "Fil小白🖤",
        "圆超-",
        "小涩猎",
        "徐不贰",
        "象腿妮妮",
        "张琪er",
        "沙耶加",
        "落落的穿搭笔记",
        "一件不重要的柿",
        "阿西kki",
        "田逾欢sirius",
        "竹内介一",
        "姜汁尔",
        "77KOiiR",
        "王大头",
        "Lin",
        "走走翼zozoey",
        "韩好甜",
        "霸丸-",
        "Wruixuan",
        "蒋欣颐_cindy",
        "CC公主殿下～",
        "是盈子哟",
        "姬佳棋Jasleeyn_",
        "小R同学",
        "活烦",
        "气氯",
        "哩酱_",
        "小程不烦",
        "门腔",
        "靳老师想开了",
        "En-Li恩利",
        "服浪西司",
        "36s",
        "Allen小鸽鸽",
        "Posy",
        "Jouraur",
        "阿庆庆庆庆",
        "水星记",
        "娜迪热Nadia",
        "3veromca",
        "愚さかな",
        "kelly买买",
        "Zach扎克",
        "切切尔西",
        "海羊",
        "沈钰莹",
        "没有阿言",
        "咖喱八妮",
        "sher✨",
        "WhySoMagnetic",
        "倩叔的时尚珠宝美学",
        "YAOLIHUI",
        "OMC",
        "我是雾月",
        "安吉拉爱幻想",
        "羽哥Fashion",
        "byFRESH",
        "ARTRISE1ST",
        "时尚星期八",
        "朱姑娘穿搭记",
        "珠珠女孩",
        "方凯瑞CARRIE",
        "福朱别懒",
        "蔓爬",
        "樱田蛙子",
        "YIYA-迪",
        "吱屋猪张张",
        "上井上",
        "明爷 Chic & Shake 💋",
        "春禹子楊",
        "高文君Wendy",
        "有机蔬菜",
        "山野的雾",
        "甄甜甜",
        "敏敏爱吃肉包子",
        "LATATA",
        "黄乔恩",
        "瑞溚",
        "洪格拉斯",
        "四月",
        "设计薯",
        "李蠕蠕",
        "春晚",
        "刘大脑袋",
        "甜仇",
        "南希Nancy_（静静和Nancy_）",
        "贝拉崽",
        "Takki",
        "阿宝真的不胖",
        "Olivier Rousteing",
        "阮青林",
        "CLOT",
        "家居薯",
        "张若兰",
        "Simone Rocha",
        "lanHwa 老年穿衣研究中心",
        "FREITAG Store Shanghai",
        "SocialBeta",
        "dilip lee",
        "-嘚哦兜-",
        "ooEchoo",
        "MARRK",
        "NAMESAK3",
        "Julio Ng 吳嘉洋",
        "钟Sir阳",
        "JASON WU",
        "CORE买手店",
        "Angelicaaaa",
        "YU PRIZE 创意大奖",
        "浪漫小林通",
        "SARAH沙啦",
        "萍宝",
        "一天一件儿子衣服的覃阿姨",
        "log_emotionalworld",
        "薛昊婧",
        "初七",
        "ZIMO",
        "about编辑部",
        "敢姑娘",
        "NOWRE现客",
        "秋刀渔渔渔",
        "FREITAG",
        "Shapeshift",
        "ZHANGZHENCHUAN",
        "荼又TOYOU",
        "小眼睛美少女",
        "阿阿类",
        "Xin Xin",
        "246h",
        "刘承羽Natasha",
        "harry",
        "尚无几",
        "斤宝吃不饱",
        "俊",
        "负暄",
        "Johanna Senyk",
        "SA夏夏",
        "詹很烦",
        "VintageCaravan",
        "nomo.",
        "LeEcaNduO",
        "THE 1975",
        "seidai",
        "Ruohan_Nie",
        "siying qu",
        "vwong1",
        "LOUIS🐒",
        "YIRANTIAN GUO",
        "-芝月-",
        "饭桶是只狗",
        "韩宇晨醒了",
        "余晚晚Wendy 💫",
        "脏霹雳TSUNAMI",
        "Lena莉安那",
        "牟帅是不帅",
        "凶手是我",
        "有竹-",
        "matchaqiu",
        "Melvin Tanaya",
        "taotao",
        "GCWZ",
        "Lolo🎭皮皮罗",
        "一朵菡",
        "陈雅雅Anya",
        "凯瑟琳大姐姐",
        "辣椒精儿",
        "木兰嬢嬢",
        "田大花",
        "小十六",
        "易小天啊",
        "Alice-金",
        "佳佳本佳",
        "哦哈哟",
        "满月岁岁",
        "sssslLin",
        "伍小雨WUYU",
        "赖皮赌徒",
        "青城山大王_",
        "羊毛要努力",
        "储镒恬CRISTIAN",
        "时髦精粒粒",
        "Nancy今天早睡了吗",
        "Xuan-",
        "甩饼大嫂",
        "曾永仪_",
        "饭饭饭大月",
        "苏苏_Sss",
        "Little硕子",
        "独角兽的粉粉",
        "八盖",
        "Taoyii桃一",
        "满满Cyim",
        "沈小俗sj",
        "Nana爱吃鱼香肉丝",
        "鹦鹉梨",
        "林森小朋友",
        "脆耳朵",
        "KIMJUN金俊",
        "囤囤er",
        "曲七",
        "六金yE",
        "许大晴-",
        "mimimia",
        "le murmure",
        "月月是斯嘉丽",
        "LINWEIWEI",
        "张慢慢ivy",
        "傻傻Marisa",
        "区区欧阳 ohyx-",
        "张楚",
        "邓二二二单",
        "yuuue-",
        "顿顿也是JANICE",
        "JNwannnng",
        "冬天脑不好",
        "全智鹅",
        "21F小王",
        "亭子",
        "Abby AB",
        "刘然Rann",
        "raywu_",
        "真真Xiii",
        "奚小西",
        "TOP姐时髦笔记",
        "希恩欧尼",
        "vickyoung",
        "王小猴儿_",
        "陈_sure",
        "杨可er",
        "小时黯",
        "怀三",
        "痣多欣",
        "郭yoo_a",
        "叫我林meier",
        "凯文枪Calvin",
        "Caitlyn凯特琳",
        "当当dangdang",
        "cherleen琳",
        "王学长Morton",
        "西西初",
        "大毛儿",
        "阿啊达",
        "罗拉大姐姐",
        "lady9酒瓶盖",
        "蛋壳发大财",
        "奥布里Aubrey",
        "miyako",
        "张诗婷NickyZ",
        "柏林草莓",
        "Haoran Li好人",
        "婉安🍒",
        "梁恒溢发生活",
        "困妮爱时尚",
        "是辣妈jane呀",
        "奔波儿灞小姐",
        "JessicaSh",
        "冉Yuan圆",
        "ARMSROCK潮流",
        "赖安安",
        "三十小拉",
        "金闪闪",
        "无敌徐徐子✨",
        "Merry君呦",
        "毛酱在这儿🍮",
        "烨小圆",
        "Nami",
        "Bobo",
        "戚风",
        "摄影薯",
        "Zachary辛巴",
        "陈思如",
        "Mike Amiri",
        "Kris Van Assche",
        "CreamyTruffle",
        "SIA不洗牙",
        "Chrison克里森",
        "J Huang",
        "MULAND木兰岛",
        "不装编辑部BuzzEdit",
        "Vienna维也纳",
        "阿喵喵Amiao",
        "安迪仕也Andy",
        "秀导LalaKoo",
        "Erin老师（海归留学版）",
        "徐豆皮",
        "谢小言",
        "阿热Re",
        "斯坦鹿_",
        "Gloria Gao",
        "Shuyu.C",
        "请叫我WERNER",
        "Jason小荐手",
        "Carlotta-卡洛达",
        "Eason不吃素",
        "imeating",
        "皮儿黑",
        "tintin听听",
        "Tuomas Merikoski",
        "天才女友GG",
        "TAVN",
        "PeanutBudder",
        "jarelzhang",
        "PhotoVOGUE",
        "赵大喜",
        "易安玺_",
        "大七仔呀",
        "绒耳朵儿",
        "DinaYalkun",
        "谷爱凌👑🖤",
        "Edi 冯（备战乌孙版）",
        "淑贤",
        "熙慧",
        "Bokeyy",
        "橘野十六",
        "Miya怡君",
        "明月",
        "泷一",
        "泡澡的山羊",
        "Coooo",
        "NicoleMinton.",
        "远近渔",
        "电商学习薯",
        "商家薯",
        "Adrianaloh",
        "豆豆早安",
        "小板鸭",
        "黄坚强",
        "谭元元",
        "Louise Damas",
        "Jeanne Damas",
        "潮流institute",
        "withmeiying",
        "卡西恩Cacien",
        "41day",
        "陈以溪Yixi",
        "聪哥玩表",
        "Poison毒老头",
        "Vick的杂货铺",
        "孟羽童Morita",
        "杰哥talk表",
        "李可表情",
        "张好吃",
        "爱表的小学生",
        "小表帝",
        "Billy看表世界",
        "绿巨人花花",
        "Kison_H",
        "淡水",
        "linlinlinlu",
        "龙哥说表",
        "新哥（奢侈品回收）",
        "倩姐说表",
        "校长与劳力士",
        "三千说表",
        "乐活最表态",
        "九条微",
        "大头大头大头儿子",
        "人文薯",
        "Lorraine",
        "中国青年报",
        "数码薯",
        "VLOG薯",
        "长寇",
        "喝一口旺仔儿",
        "嘉嘉馥友",
        "至春禾",
        "亭亭别乱跑",
        "荷里寒",
        "Fofo酱",
        "胡阿小小",
        "施梦露",
        "大美牛",
        "长歌要努力吖",
        "十万八千里哩",
        "阿诺anuo_",
        "夜夜子ZR",
        "小风铃",
        "吃花女",
        "绯凉儿",
        "夏弃疾_（下乡版）",
        "零青子",
        "犀利妈Ly媛媛",
        "彼得的彼",
        "160的橙子呀",
        "Yoobaby정민",
        "大琳",
        "张鱼小丸子",
        "小小小小白",
        "一只闪闪",
        "_梦依",
        "我超耶啵（软装中）",
        "Babeei张张",
        "冷脸小刘",
        "mmmmmaby",
        "Aria西西呀",
        "白金时代",
        "唐泽",
        "古古本古",
        "其寺Mar",
        "张怯怯",
        "大中小天团",
        "孙一宁is",
        "柳铃铛",
        "草鱼爱吃鱼zzz",
        "沉甸甸的陈点点",
        "马宝莉",
        "Whenwhen在此",
        "小桑菜奈",
        "圈圈日记本📕",
        "龚喜恭喜～",
        "胡艺馨",
        "疏疏（男神收割机）",
        "阿King哥",
        "Mzzio_Fun",
        "红鹤陈易欣",
        "三金yo",
        "Ywenlor",
        "瓦瓦妮莎",
        "卓玛可以",
        "邱明AKi",
        "是倩倩子呀",
        "Leenan_",
        "都市梨人唐舒格",
        "51oe",
        "张小某",
        "或野",
        "151盐妹穿搭日记",
        "ChicSavages",
        "沙沙sasa",
        "是西西吖",
        "褒义词",
        "Winnie文",
        "Honey酱",
        "Mona张绮贤",
        "R.R",
        "豆豆_Babe",
        "彤姐很忙",
        "灵小跑",
        "ashui-AS",
        "一只叫狗狗的猫",
        "我是刘梦娜",
        "一鹿",
        "乜-",
        "是冰",
        "黄哈哈",
        "卢阿大",
        "加柒不加糖",
        "*BURGERCHOI*",
        "肉松小贝🐍",
        "小鱼同学",
        "芮米Remi",
        "shen02",
        "hei.n",
        "CW超超_超能吃",
        "秀芹在此",
        "良潇",
        "唐XX",
        "Mm是Ivy",
        "风干猪肉",
        "楷文kaiwen",
        "关大宝",
        "哟米yomi",
        "OooOlivia奥莉蕉",
        "SlayHouse",
        "豆豆本豆",
        "-sichsite",
        "百万个垃圾桶",
        "张大碗子",
        "李程程_cc",
        "文森特别6",
        "陆仙人",
        "1534",
        "RonRon",
        "石榴滋滋",
        "王菊",
        "白鹿",
        "李斯丹妮",
        "黄龄",
        "潮流情报官",
        "c柔柔",
        "涂涂",
        "阳光男孩eyex",
        "刘梦LapMoby",
        "是美丽小金",
        "杨喜茶",
        "涂你什么吖",
        "郑厦荣",
        "徐祥",
        "浅葱喵asagiinyo",
        "璐可爱",
        "SHANRAN",
        "蔡发发",
        "偹爷",
        "任小艺_",
        "杨意子_",
        "王不烦",
        "多老师Dora",
        "诺娃Nova",
        "11May",
        "Yi_大头",
        "莉贝琳",
        "周周周耶啵",
        "Vireus",
        "夏奈儿",
        "猫小柒柒",
        "若文的复古生活-",
        "五代第一人间食人花",
        "ahobbit",
        "小野田田",
        "Wyer歪尔",
        "陈吃饭_",
        "巍宝Julie",
        "潮流薯",
        "Double P",
        "妮蔻Dreamer",
        "小曾顿时大喜",
        "侍知🐷",
        "林诗琦NICKI",
        "Maggienotes",
        "懒懒田心",
        "蓝蓝哒改造",
        "Building_Lou",
        "阿惹妹妹",
        "Zacky草蛋",
        "头发多小腿粗",
        "JamieZHAO_",
        "BettySays",
        "SYOU",
        "洪潇Hanna",
        "阿以莎",
        "小蕾Diana",
        "丸十一",
        "玉琪uk",
        "剪刀JENCY",
        "方也小施",
        "DingYi",
        "cocoon.upcycle",
        "丁一瑶",
        "小猪晗晗_手工制作🐷",
        "裁缝羊妈JLY",
        "小章鱼Iris",
        "知识悍妇于发发",
        "sky盖盖",
        "没了底",
        "是捞捞呀😉",
        "Quni大冰",
        "HazelWu_",
        "崔萬一",
        "海獭🤓",
        "南栀",
        "Sunny陳日天",
        "平常心在日本",
        "橙子玉米烧",
        "Chris姐姐_",
        "Alex绝对是个妞儿",
        "Simon菠萝",
        "谢榭",
        "次啦CILA✨",
        "小红薯600425DC",
        "Kira岐牙",
        "高源Kaiko",
        "宇子的地球日记",
        "MissChill知邱",
        "其恩酱",
        "RedCrush",
        "SENIOR FASHION HUB",
        "朝越南",
        "Frida弗里达",
        "我没买",
        "Michelle Song 泽卿",
        "SuperNancy",
        "SOLSOL LI",
        "米兰Rita姐",
        "Ewilling",
        "贺不怂Haro-",
        "Jack邝楠",
        "LH_口口",
        "王奕芝Natalie",
        "Fabrique",
        "AHALOLO",
        "康康和爷爷",
        "闰土",
        "是于桐桐",
        "王家伍子",
        "Meggy-Y2 House",
        "莉莉东 LiliDong",
        "橘男儿",
        "我是安揪",
        "金智焕",
        "D_DWen",
        "徐听雨",
        "Limhay",
        "卡戴三",
        "AlessoYoko",
        "Selva_笛子",
        "24h不自动贩卖机",
        "饭饭又饿了",
        "胡恩慈",
        "无星z",
        "小桐小桐",
        "苏琪哥_",
        "依涵",
        "Millypang",
        "尹可以",
        "MirrorWang",
        "小滴DRAMA",
        "Darrrrcy",
        "梁上進",
        "YAMI大哥大",
        "Angelo 桃子",
        "木村由佳",
        "刘蓓霏",
        "腿精蔗大王",
        "纸片片儿",
        "我是411（日常花絮混更版）",
        "草莓",
        "小乔切克闹",
        "老八捌",
        "不及丿",
        "远山乔",
        "叶初婳",
        "子颂颂啊",
        "Li十七呀",
        "第十秋",
        "顾小思",
        "游游",
        "影子大王",
        "姬小妖",
        "刘子菲",
        "SOURCE",
        "钱钱Yaa",
        "星野",
        "恰恰是我阿",
        "cecici-",
        "wowimK",
        "何慧香",
        "彻之三",
        "Dai188",
        "後苏联少年",
        "BLACKBAB",
        "罗岚珊RHO",
        "杨十一",
        "彭咔咔_",
        "壶提提",
        "野爷YOKI",
        "Ning1",
        "狂躁栀子花",
        "这是邱天",
        "时髦人Sunnie",
        "-木枝_",
        "HiroVINTAGE",
        "Drasson",
        "瓶瓶pingping",
        "maming_me",
        "腻腻ninii",
        "大锤妹妹",
        "Yvonne Du",
        "瑞雪Cloe",
        "kathyyymm",
        "小野萝拉",
        "梅桢小姐姐",
        "贝小小七",
        "阿la葵葵",
        "两只仙女三米一",
        "黑心少奶奶-陈诗情",
        "小杨不热🦈",
        "黑黑圣",
        "野十四",
        "Maxxxx",
        "bulunano（备婚版）",
        "小黄油块跑",
        "angelstyle",
        "MeowFox",
        "M大王",
        "hey我是咖咖",
        "刘元凯KK",
        "徐娇",
        "王霏霏Fei",
        "袁娅维TIA RAY",
        "七颗_",
        "是麋鹿呀",
        "nana桑",
        "大嘴Yuki",
        "谭湘君Jjun",
        "小芽芽YAYA",
        "酸性脾气cgw",
        "瑶三岁dazzling",
        "熊米栗",
        "Momolife",
        "呀呀呀甜鹅",
        "蔡妮妮nini",
        "敬敬子",
        "-大安安",
        "Channel8_",
        "李马特",
        "猫屎雪碧",
        "夏诗文shwin",
        "程十安an",
        "缺点钙",
        "火山大王",
        "UG你大哥",
        "shgiook",
        "Wanyii",
        "小江别熬夜了",
        "yourslynnn",
        "护肤狂人宋百万",
        "偶是屁雪",
        "Baor在德国当裁缝",
        "咸咸子",
        "外卖到了O",
        "小小只🐇",
        "bibibiu",
        "YEL",
        "大F",
        "svsvvv",
        "一菲iffy",
        "贝蒂Beidi",
        "桃子小姐Vincci",
        "Kim可乐金",
        "沫沫姐姐",
        "张阿星-",
        "阿倩dd",
        "果果队长",
        "其斤小小",
        "Welling张歪灵",
        "彩妆师Apple",
        "70是麒麟",
        "六神abi",
        "眼仔",
        "纳豆奶奶",
        "大宋宋Tiffany",
        "黎子雾",
        "蔡文玉",
        "允仔🌿",
        "南笙",
        "时尚芭莎",
        "孟佳",
        "徐璐LULU",
        "Cecilia宋妍霏",
        "王涵大朋友",
        "杨凯雯rr",
        "SeeyouKeya",
        "梦梦要努力呀",
        "鸽鸽dovie",
        "mialiuuuu",
        "星尔Maggie",
        "SammyOnTheWay",
        "可乐宇仔",
        "王鹿子",
        "单眼皮的妮可",
        "俺4小赵",
        "比巴卜是气泡",
        "老马Marius",
        "Milly米粒",
        "帕梅拉Pamela Reif",
        "小红薯6333233D",
        "施柏宇patrick",
        "安吉林Angelene",
        "憨桃人儿",
        "樹一SENSENG",
        "小双双Jodie",
        "REDstudio",
        "陈细粒 Vannis",
        "小蜗周周_（备婚版）",
        "范阿混",
        "MC萱萱RubyLi",
        "Yeo_心",
        "鹿一家🦌",
        "AmandaX",
        "唐一白",
        "Lady Melody",
        "是公子陈呀",
        "雨七七",
        "孙怡静cristine",
        "妍晕yyanyun",
        "玉儿佐佐",
        "鹿的角",
        "AIC菜菜啊",
        "浪味鲜儿",
        "行哥哥哥-",
        "安娜安娜",
        "Susan苏",
        "麦芒coucoujane",
        "麦芒coucoujane",
        "蔫豆芽二",
        "Sally 莎梨",
        "君君子Jun",
        "扒圈二姐",
        "Miss橙子小姐",
        "美洋MEIYANG",
        "陈一丁Dingslook",
        "薛子+1",
        "Ciger",
        "小仙女汁",
        "土拨鼠",
        "明仔和二毛子",
        "珊珊子",
        "Purple阿紫",
        "陈溥江",
        "丹麦小马达",
        "大魔王呸",
        "朱佳航",
        "林游锐-",
        "金六五",
        "Moogie",
        "小甜椒Rukia",
        "郭十十呢",
        "温思儿",
        "七个羊_",
        "温妞妞",
        "喜感claire",
        "韩安娜呀",
        "爱臭美的狗甜儿",
        "唐心蛋Candice",
        "就是Cathy啊",
        "思文文文",
        "姜思达",
        "熊静JING",
        "ByMei",
        "熊静JING",
        "林小厦",
        "Lala_",
        "万籽麟",
        "小红书REDesign",
        "Teayyaaa",
        "郭卷毛",
        "毛豆豆的anna妈咪",
        "连卡佛Lane Crawford",
        "FARFETCH发发奇",
        "BrandyMelville",
        "IreneYeah",
        "Winnie Tang",
        "爱笑的小安安",
        "亚妞er",
        "饭饭麻麻菜菜",
        "三x三",
        "圆圆",
        "帅气小夹心",
        "菜盟主",
        "迪克婷仔",
        "Killelvis",
        "隔壁老王",
        "萱婶儿",
        "顾嘉悦Melody",
        "蛋挞子",
        "还是那个李",
        "朴瑞颖",
        "小紧张的虫虫",
        "脆脆",
        "AyanWang",
        "麻玛吉儿",
        "了了or",
        "了了or",
        "阿颗姐姐",
        "jenlee",
        "雪子💮",
        "lu.meng",
        "ImFeiteng✨",
        "GreenHeadFrog",
        "Keyunnnn",
        "铁锤妹妹",
        "周芊芊",
        "常YI生",
        "ImEva",
        "森森小姐在打滚",
        "DiONeWAnG| 造型师stylist",
        "胖秋有点甜",
        "谭老师tam",
        "傅宇宙",
        "傅宇宙",
        "丝丝沈",
        "胆大王-",
        "miss曼a_a",
        "树皮",
        "💛小野智恩💜",
        "Skiko小希子",
        "Mews珠宝",
        "Mia_猫卟",
        "阿紫啊",
        "胡一",
        "欧阳娜娜Nana",
        "加糖的梨",
        "小天狼星LM",
        "小轩轩儿",
        "七兮的学习日记",
        "不觉晓",
        "唐唐一小只",
        "🎀Summer_MoMo🌸",
        "🎀Summer_MoMo🌸",
        "是小奕同学吖",
        "一只可爱绒",
        "大宝姐在日本",
        "陈靓靓",
        "1ITTLEFISH",
        "Z-HeLen-",
        "啾镜呀",
        "Mhisu-",
        "KK是阿珂吖～",
        "WANGSMAN_",
        "佳佳睡不着",
        "林堅定_",
        "美七七美七",
        "刘刘肯德鸭",
        "-Lenayan",
        "李焕秋",
        "小红薯62B328DF",
        "小红薯62B42B07",
        "小红薯62BF0A64",
        "小红薯62B326CB",
        "喵喵Marie",
        "猫选之子的荣耀",
        "林如清",
        "一身琉璃白QAQ",
        "一一",
        "芋圆啵啵子",
        "林一蓝",
        "静仔",
        "酥梦asu",
        "董林萧",
        "吱吾朱",
        "Steve吴先生",
        "跳仔士多",
        "-谢安然-",
        "千反田甜子",
        "小野汀",
        "恩桃",
        "西瓜冰沙佳小卷",
        "阿沛沛Peiii_",
        "エ虫虫🐛",
        "颖儿",
        "云在在",
        "SenKaori",
        "LilTok",
        "Anne.CHEN",
        "我睡不醒",
        "神奇菟菟",
        "ok兔！",
        "糖炒栗子呐🌰",
        "小红薯6489477B",
        "老娘就是小五酱",
        "圣里",
        "给你一颗球",
        "Etsuna悦奈inTokyo",
        "momo",
        "清喧喧",
        "韦的的呀！",
        "故梦三寻",
        "玥儿🌴穿搭",
        "汉尚华莲汉服",
        "疼椒牛肉面",
        "CiciJennyGu",
        "来杯奶盖拿铁呀",
        "细路酱",
        "龙雪喵",
        "栗队长Momoco",
        "圆喵喵",
        "撒撒🐾",
        "暖暖是个小可爱",
        "珊了个儿",
        "那咋了",
        "lulu可以吃不胖吗",
        "萌酱旅行",
        "万小乖🐝",
        "甜札.",
        "你的小可爱💗",
        "小音超爱吃咸蛋黄饼干",
        "是欣欣o",
        "乖乖龙滴咚",
        "奶油贝利尼",
        "焦糖。",
        "一百个叶杨",
        "氧文卿🥂",
        "仙朵拉",
        "hhh",
        "Tongkiiiyo-",
        "克里希__",
        "豹脾气Freya🐆",
        "Chloe Moretz",
        "泫雅_hyuna",
        "Little小只",
        "来酱在东京",
        "纯妹妹",
        "TongTong_TongTong",
        "密丝鱼🐟",
        "月月LUNA(生龙凤胎版)",
        "小骨啊骨",
        "辛芷蕾",
        "莉娜LINA",
        "杨羊🐑",
        "姚晨",
        "娜扎",
        "你家婶子",
        "Foxxxxxx1813",
        "袁姗姗",
        "米儿姐姐",
        "Kira小魔女",
        "尤尤在路上",
        "momo.",
        "DanDan",
        "波点裤～92",
        "维米 SONG",
        "棉花糖Cindy",
        "周指导",
        "老绵羊仙女",
        "拎壶冲",
        "大萌Mon",
        "小蘑菇Lo1",
        "力力力",
        "吴晓晓",
        "阿祖测评日记",
        "idol范儿",
        "果儿Dora（宠粉版）",
        "肉雯",
        "肉雯雯的日常",
        "是小板鸭吖_",
        "hikida",
        "_西门爷爷",
        "混世老仙女啊",
        "TTTTAO",
        "文仔Deemo",
        "Neyra",
        "严痘痘",
        "喵二两七",
        "诺诺陈",
        "yiyi.xu",
        "蕙子呀蕙子",
        "Ashley_艾欣（哺乳期）",
        "幸儿Vanilla",
        "红酱redhush",
        "CallMe王阿姨",
        "Bonita",
        "胡安琪anki",
        "皮卡秋的好物分享",
        "leevane",
        "kkkkke",
        "右岛",
        "Leeanyn",
        "小红薯D1545F63",
        "奶酪是个球_",
        "Katrinaaa",
        "是生姜的姜啦",
        "Slemonuk",
        "书院街耿直的豌豆",
        "一颗妮栗",
        "球球你了",
        "x1uen😴",
        "甜甜兔子酱🐰",
        "喂～",
        "陈呐喊lyn",
        "迪迪哼哼唧唧",
        "Keeeeeya",
        "Tweety",
        "lxlululu",
        "球大王",
        "小阿小阿小_",
        "美护买手薯",
        "公姓的小屋",
        "转身离开@……芮",
        "小陈樱桃",
        "汜涇Tsing",
        "少吃一点吧🍔",
        "闻夏",
        "gogoboi",
        "摩尔家陈泰山",
        "Leaf Greener 叶子",
        "赵梦玥UUU",
        "Kakakaoo-",
        "李晖616",
        "Mia-Kong 🐉",
        "CKverymuch",
        "一朵兒XR",
        "Ivy11.",
        "毛喜喜Chriiu",
        "JJJqi",
        "ReReNee",
        "芹新一",
        "小镜静_",
        "方恰拉",
        "Jojoye",
        "樱桃叽歪酱",
        "徐大胆",
        "陶喜儿",
        "月半轩Bubble",
        "猴小萌么么哒",
        "迷人可达鸭",
        "一位甜心",
        "KarlieKloss",
        "JJ啾啾",
        "licheeeeeo",
        "Suesue叔叔",
        "何蓝竹",
        "怪人阿瓜",
        "小皮酱",
        "橘子的橘汁橘皮",
        "陈采尼",
        "宝藏女孩阿花",
        "查理",
        "丘比特鲨",
        "若是梦呢",
        "管阿姨",
        "toni郑焱焱",
        "张憔悴",
        "aubrey",
        "杨肉面",
        "一只卡门仔",
        "关做作",
        "甜甜Sarah",
        "小红薯20431A75",
        "胡星星",
        "桃源",
        "应采儿DingDing",
        "長嘴经纪",
        "晴哥拉",
        "刘说说",
        "房夏多doudou",
        "💜mini💜",
        "陈泓辰 奶爸版",
        "丸子胡了",
        "来来gogo",
        "尤金Eugene",
        "菜菜",
        "陈叔叔的日常",
        "womeili",
        "秦楚妃",
        "曼雪Snowlovelife",
        "吴十七",
        "小橙没睡醒",
        "Neenosleep",
        "Aaaaki-",
        "吾蕊",
        "ssxiaa",
        "种瓜养花",
        "游泳圈_",
        "Well-being",
        "吴昕",
        "你的遥",
        "启豪Kaiho",
        "包先生",
        "-饭二-",
        "winjoo",
        "ItsBonnyBonny",
        "阿吃怪",
        "Lisa",
        "LUMINA",
        "面团",
        "打哈欠的大狮子",
        "Blake_Lee",
        "大睿睿",
        "Irene林恩如",
        "手边巴黎",
        "nanmeiimei",
        "Sherman-closet",
        "钱邦宁",
        "建筑师",
        "回响ECHOBAR",
        "小浪花超凶",
        "Swan大鹅姐",
        "黑漆漆漆漆",
        "黄非易（痴迷网球版）",
        "HolaJin",
        "Karennnn圆不圆不圆",
        "Snow圆圆Tokyo",
        "LoveySLook",
        "乔卡Joika💧",
        "Sherry艺泠",
        "刘知宜BananaFashion",
        "Biano",
        "IAMIAN",
        "kakarmen",
        "陈白羊",
        "李一一",
        "Giovanna大白哥",
        "甜橙小王🍊",
        "claraischill",
        "KING09",
        "保罗的甜品大师",
        "Huhululu",
        "王冻梨_",
        "Gemma",
        "朱格桑🌸",
        "李大胆儿",
        "芙罗拉拉Flora.🌷",
        "amlavi",
        "元南",
        "Anitafeeling",
        "墨池墨吟",
        "Matchatoto",
        "牙牙_YOYA",
        "竖条纹",
        "chika photo",
        "草莓味硬糖",
        "Yaco",
        "熊猫cc_",
        "小鬼大大酱",
        "尹子一一啊",
        "穆熙妍Crystal",
        "AdrianneHo",
        "麻烦跳跃不停的Cc",
        "张醉墨",
        "Sanne_Vloet",
        "HiyaSonya",
        "NICOLAB",
        "Stephanie 🌹",
        "小红薯60427249",
        "李李奉",
        "chaiwofong",
        "东瓜嘀嘀",
        "迪拜奶奶",
        "侃烃",
        "Lazymonki",
        "王十五五五",
        "Tammy",
        "_dearKiM✨",
        "廖蹄花",
        "Chihiro",
        "肚子好2",
        "香蕉牛奶好好喝哟",
        "板长_",
        "ssoa",
        "江疏影",
        "黄灿灿acan",
        "魔法少女🌺",
        "浮生八记Augmap",
        "WasabiSalt",
        "KAIGER 凯歌儿",
        "fakesheep",
        "karenheshi",
        "洪辰瑶Kelly（洪千辰）",
        "奔波霸波奔儿🥕",
        "木兮子兮若",
        "计凯蒂",
        "huanglidan",
        "王紫璇",
        "张醋醋er_",
        "🦁",
        "KiKi_小七",
        "黄十一",
        "小红薯5D4FAF1A",
        "Viannalau",
        "高高大王",
        "小红薯3F20EDAF",
        "iam肥肥羊",
        "umbrella",
        "何穗",
        "韩火火",
        "彤妹妹yo",
        "哈齿小姐陈艾佳",
        "一只潘",
        "Eokney",
        "一碗胡辣汤女士",
        "小红薯8C92A880",
        "新地是Cindy",
        "小波酱",
        "汝加七",
        "怡呀yo🎧",
        "surewant",
        "奶哥今天不在家",
        "早睡教教主",
        "eggbaby",
        "孟汝J",
        "易梦玲",
        "y允七",
        "没有纯",
        "曾鹿儿",
        "胡安如",
        "削梨Sherry"
    )

    var isBackFromTalentPage: Boolean = false

    val hander: Handler = Handler(Looper.getMainLooper())

    init {
//        searchDataList.add("葡萄大人");
//        searchDataList.add("沈辞er.");
//        searchDataList.add("山城小栗旬的理发日记");
//        searchDataList.add("白昼小熊");
//        searchDataList.add("鲑鱼子");
//        searchDataList.add("icycream");
//        searchDataList.add("Sinoe泷吟");
//        searchDataList.add("绒耳朵儿");
//        searchDataList.add("是你阿季呀");
//        searchDataList.add("天总");
//        searchDataList.add("区区欧阳 ohyx-");
//        searchDataList.add("Asu丶艾斯");
//        searchDataList.add("谷梨梨");
//        searchDataList.add("爱拍照的玉老师");
//        searchDataList.add("夙胤言生");
//        searchDataList.add("是西西吖");
//        searchDataList.add("陈都灵");
//        searchDataList.add("黄饱饱了");
//        searchDataList.add("BettySays");
//        searchDataList.add("一条辣cc");
//        searchDataList.add("刘梦LapMoby");
//        searchDataList.add("小骨啊骨");
//        searchDataList.add("景甜");
//        searchDataList.add("朱珠ZhuZhu");
//        searchDataList.add("陆鹫贰黑");
//        searchDataList.add("青日子子");
//        searchDataList.add("王悦伊");
//        searchDataList.add("Clarice的硕士妈咪");
//        searchDataList.add("chilly香菜");
//        searchDataList.add("在下悟空是也");
//        searchDataList.add("阿靖靖靖");
//        searchDataList.add("彼岸的水坑");
//        searchDataList.add("全智鹅");
//        searchDataList.add("140斤的芋头");
//        searchDataList.add("腻腻ninii");
//        searchDataList.add("独角兽的粉粉");
//        searchDataList.add("乔欣Bridgette");
//        searchDataList.add("六一不是猫");
//        searchDataList.add("丁儿丁儿");
//        searchDataList.add("方枪枪Monica");
//        searchDataList.add("Babeei张张");
//        searchDataList.add("Aikoko_");
//        searchDataList.add("油炸QQ糖");
//        searchDataList.add("青羽Vic");
//        searchDataList.add("仙女软本人");
//        searchDataList.add("桃瑞思");
//        searchDataList.add("JZ");
//        searchDataList.add("只小萌");
//        searchDataList.add("叶芳伶link");
//        searchDataList.add("Kilory_利利");
//        searchDataList.add("满月岁岁");
//        searchDataList.add("白未ALI");
//        searchDataList.add("bibibiu");
//        searchDataList.add("POSHEPOSE高定如心");
//        searchDataList.add("李马特");
//        searchDataList.add("爆胎草莓粥");
//        searchDataList.add("张元气");
//        searchDataList.add("零青子");
//        searchDataList.add("其寺Mar");
//        searchDataList.add("FortyLions");
//        searchDataList.add("钢铁猛懒");
//        searchDataList.add("Tina面料测评");
//        searchDataList.add("张琪er");
//        searchDataList.add("Taoyii桃一");
//        searchDataList.add("快把柚子带走");
//        searchDataList.add("范00");
//        searchDataList.add("主播潘小蓉");
//        searchDataList.add("大美牛");
//        searchDataList.add("南北芝麻糊");
//        searchDataList.add("冬夏&安柒❤");
//        searchDataList.add("陶四七-");
//        searchDataList.add("钱棒棒");
//        searchDataList.add("阿葛梨梨");
//        searchDataList.add("妤Yuki");
//        searchDataList.add("汪澜的妹妹");
//        searchDataList.add("小野智恩");
//        searchDataList.add("暖傲男");
//        searchDataList.add("未岚RAN");
//        searchDataList.add("泽光君");
//        searchDataList.add("大锤妹妹");
//        searchDataList.add("帆帆吃饱了");
//        searchDataList.add("苏苏_Sss");
//        searchDataList.add("启豪Kaiho");
//        searchDataList.add("我是球球菌");
//        searchDataList.add("甩饼大嫂");
//        searchDataList.add("阿毓yuyu");
//        searchDataList.add("my-name-is-MM");
//        searchDataList.add("炸鸡欧尼Oni");
//        searchDataList.add("姣姣来了");
//        searchDataList.add("一只希贝-");
//        searchDataList.add("曾鹿儿");
//        searchDataList.add("开会的大会");
//        searchDataList.add("小鬼大大酱");
//        searchDataList.add("安吉林Angelene");
//        searchDataList.add("ChiliCharlotte");
//        searchDataList.add("风华绝代的石榴姐");
//        searchDataList.add("E吖E小姐");
//        searchDataList.add("我是袁一琦");
//        searchDataList.add("Agent辰辰");
//        searchDataList.add("彭彭不吃饭");
//        searchDataList.add("杨意子_");
//        searchDataList.add("是Nini啊--");
//        searchDataList.add("漫妮Fiona");
//        searchDataList.add("林诗琦NICKI");
//        searchDataList.add("Khaless3");
//        searchDataList.add("夜忙症");
//        searchDataList.add("曲七");
//        searchDataList.add("bulunano（备婚版");
//        searchDataList.add("乐仔Levi");
//        searchDataList.add("全全");
//        searchDataList.add("钟晨瑶ZCY");
//        searchDataList.add("婉婉酱");
//        searchDataList.add("豆豆本豆");
//        searchDataList.add("关大宝");
//        searchDataList.add("托托Toto");
//        searchDataList.add("刘大大");
//        searchDataList.add("小怡同学");
//        searchDataList.add("其恩酱");
//        searchDataList.add("Darrrrcy");
//        searchDataList.add("李施嬅 Selena");
//        searchDataList.add("王秀竹");
//        searchDataList.add("Savislook");
//        searchDataList.add("油菜花花");
//        searchDataList.add("阿雅");
//        searchDataList.add("六金yE");
//        searchDataList.add("黑娃呦～");
//        searchDataList.add("Luna教主");
//        searchDataList.add("小甜椒Rukia");
//        searchDataList.add("苏半月");
//        searchDataList.add("梁夹心");
//        searchDataList.add("大大木和小小嘟");
//        searchDataList.add("一菲iffy");
//        searchDataList.add("三加一");
//        searchDataList.add("阿紫");
//        searchDataList.add("120斤的欣怡");
//        searchDataList.add("陳山聰");
//        searchDataList.add("挖宝女孩");
//        searchDataList.add("空贝贝贝贝贝");
//        searchDataList.add("JNwannnng");
//        searchDataList.add("辣椒精儿");
//        searchDataList.add("NexT1DE-胡烨韬");
//        searchDataList.add("郁可唯");
//        searchDataList.add("依涵");
//        searchDataList.add("Winnie Tang");
//        searchDataList.add("是公子陈呀");
//        searchDataList.add("-谢安然-");
//        searchDataList.add("脑洞少女T");
//        searchDataList.add("鸭学长开课啦");
//        searchDataList.add("Little Nothing");
//        searchDataList.add("项炸炸");
//        searchDataList.add("文仔Deemo");
//        searchDataList.add("SOURCE");
//        searchDataList.add("熊夫人");
//        searchDataList.add("你的年年");
//        searchDataList.add("辣椒炒睿");
//        searchDataList.add("丸子Tina");
//        searchDataList.add("熊猫叮");
//        searchDataList.add("黎篱");
//        searchDataList.add("小岛大瑀");
//        searchDataList.add("范玮琪");
//        searchDataList.add("Elena朴");
//        searchDataList.add("张阿星-");
//        searchDataList.add("AyanWang");
//        searchDataList.add("Skiko小希子");
//        searchDataList.add("hello我是沱沱");
//        searchDataList.add("吕燕");
//        searchDataList.add("彭王者是小洋");
//        searchDataList.add("handsmood");
//        searchDataList.add("李李奉");
//        searchDataList.add("吕颖Ivy");
//        searchDataList.add("宫沐泽");
//        searchDataList.add("憨桃人儿");
//        searchDataList.add("林子熙");
//        searchDataList.add("一粒维c");
//        searchDataList.add("刘小被儿不是盖的");
//        searchDataList.add("画画-");
//        searchDataList.add("Akiiiko");
//        searchDataList.add("小山菜奈");
//        searchDataList.add("小小小小白");
//        searchDataList.add("陈若仪");
//        searchDataList.add("胡艺馨");
//        searchDataList.add("黄婷婷");
//        searchDataList.add("周洁琼");
//        searchDataList.add("ssserena hu");
//        searchDataList.add("陈大事");
//        searchDataList.add("你是一个果子");
//        searchDataList.add("陈奕伊chen11");
//        searchDataList.add("山南以北");
//        searchDataList.add("徐大脸吖");
//        searchDataList.add("赵昭仪");
//        searchDataList.add("MissCharming");
//        searchDataList.add("短头花");
//        searchDataList.add("y=ax+b");
//        searchDataList.add("大果大果赵大果");
//        searchDataList.add("JoJo酱");
//        searchDataList.add("李李念禾");
//        searchDataList.add("卢佳卢小佳");
//        searchDataList.add("小小莎老师");
//        searchDataList.add("是花总吖");
//        searchDataList.add("咸野");
//        searchDataList.add("何宣林");
//        searchDataList.add("俞香子");
//        searchDataList.add("Imzsy12");
//        searchDataList.add("当然是当");
//        searchDataList.add("Yi_大头");
//        searchDataList.add("其斤小小");
//        searchDataList.add("苦苦小羊");
//        searchDataList.add("香菱鼓瑟");
//        searchDataList.add("桃子小姐Vincci");
//        searchDataList.add("单眼皮王公子");
//        searchDataList.add("大洪洪");
//        searchDataList.add("一井豚");
//        searchDataList.add("林小宅");
//        searchDataList.add("莉贝琳");
//        searchDataList.add("Yuki雪雪");
//        searchDataList.add("陈醉醉");
//        searchDataList.add("洪潇Hanna");
//        searchDataList.add("沙沙sasa");
//        searchDataList.add("99u");
//        searchDataList.add("老敏哥");
//        searchDataList.add("怡含怡含");
//        searchDataList.add("贝小小七");
//        searchDataList.add("sssslin");
//        searchDataList.add("抠搜 ya ya 爱挖宝");
//        searchDataList.add("Sherman-closet");
//        searchDataList.add("仓大佬");
//        searchDataList.add("泥美");
//        searchDataList.add("Yuri睿哥");
//        searchDataList.add("东瓜嘀嘀");
//        searchDataList.add("小梦李孟羲");
//        searchDataList.add("林堅定_");
//        searchDataList.add("彭杨");
//        searchDataList.add("七颗_");
//        searchDataList.add("阿King哥");
//        searchDataList.add("牙膏Soda");
//        searchDataList.add("球球你了");
//        searchDataList.add("刘芸");
//        searchDataList.add("吴敌！");
//        searchDataList.add("郭晓敏");
//        searchDataList.add("saisai");
//        searchDataList.add("王塔塔");
//        searchDataList.add("松青学姐");
//        searchDataList.add("陈一丁Dingslook");
//        searchDataList.add("-Lenayan");
//        searchDataList.add("Milly米粒");
//        searchDataList.add("笑笑同学啊");
//        searchDataList.add("迪拜奶奶");
//        searchDataList.add("Nancy今天早睡了吗");
//        searchDataList.add("PD大人");
//        searchDataList.add("aboutyee");
//        searchDataList.add("芒果草莓干");
//        searchDataList.add("刘一口");
//        searchDataList.add("壹五五");
//        searchDataList.add("美洋MEIYANG");
//        searchDataList.add("零零要早睡");
//        searchDataList.add("康康和爷爷");
//        searchDataList.add("木子卓儿");
//        searchDataList.add("王一浩同学");
//        searchDataList.add("萧潇XiaoXiao");
//        searchDataList.add("王乙孑川");
//        searchDataList.add("曹斐然");
//        searchDataList.add("郭十十呢");
//        searchDataList.add("陈姝君");
//        searchDataList.add("雨七七");
//        searchDataList.add("小钰");
//        searchDataList.add("陈思如");
//        searchDataList.add("AnotherOlivia");
//        searchDataList.add("石大小姐");
//        searchDataList.add("大码胖佳佳");
//        searchDataList.add("仲qiuqiu");
//        searchDataList.add("是圆脸考拉");
//        searchDataList.add("小肥呼呼");
//        searchDataList.add("因为我是tyt");
//        searchDataList.add("田原");
//        searchDataList.add("吴佳烨");
//        searchDataList.add("曹琳琳");
//        searchDataList.add("吴芊盈");
//        searchDataList.add("姜京佐");
//        searchDataList.add("ice艾晓琪");
//        searchDataList.add("吴糖白桃茶");
//        searchDataList.add("卢杉");
//        searchDataList.add("伊伊CHENE YI");
//        searchDataList.add("啊川");
//        searchDataList.add("胖梨阿姨");
//        searchDataList.add("薛凱琪");
//        searchDataList.add("痞幼");
//        searchDataList.add("李斯羽");
//        searchDataList.add("丁禹兮");
//        searchDataList.add("VeryDee");
//        searchDataList.add("哪里哪里吖");
//        searchDataList.add("Mini大悦");
//        searchDataList.add("陈小纭");
//        searchDataList.add("比鲁斯.雯");
//        searchDataList.add("王漪淼");
//        searchDataList.add("赵大喜");
//        searchDataList.add("车晓");
//        searchDataList.add("三木三木呢");
//        searchDataList.add("北小北-珍氏珠宝");
//        searchDataList.add("曾一萱");
//        searchDataList.add("迪小小");
//        searchDataList.add("叶是");
//        searchDataList.add("刘知宜BananaFashion");
//        searchDataList.add("谢可寅");
//        searchDataList.add("子回頭是浪");
//        searchDataList.add("杨云绚");
//        searchDataList.add("演员林源");
//        searchDataList.add("贺聪HeCong");
//        searchDataList.add("小镜静_");
//        searchDataList.add("芝芝早安_");
//        searchDataList.add("许馨文");
//        searchDataList.add("简单手工");
//        searchDataList.add("万小七");
//        searchDataList.add("阿月月ayue");
//        searchDataList.add("瘦宅下饭菜");
//        searchDataList.add("井柏然");
//        searchDataList.add("学妹娜娜");
//        searchDataList.add("苓妹妹");
//        searchDataList.add("谢小雨amy");
//        searchDataList.add("米儿姐姐");
//        searchDataList.add("王子文");
//        searchDataList.add("皮皮夏的评测日记");
//        searchDataList.add("Alfie 的Angel");
//        searchDataList.add("宁静");
//        searchDataList.add("悦恩oni");
//        searchDataList.add("ColaCat噗");
//        searchDataList.add("AndreaAndJayden");
//        searchDataList.add("卡琳娜和揉西");
//        searchDataList.add("MAXINE时尚日记");
//        searchDataList.add("痣女如花");
//        searchDataList.add("勇敢肉肉");
//        searchDataList.add("大黄h");
//        searchDataList.add("上优来啦");
//        searchDataList.add("三吴学姐");
//        searchDataList.add("优子姐姐");
//        searchDataList.add("饭饭又饿了");
//        searchDataList.add("余科目美食");
//        searchDataList.add("团子姐妹");
//        searchDataList.add("任小艺_");
//        searchDataList.add("阿吴");
//        searchDataList.add("Ann大爷");
//        searchDataList.add("萌儿youm");
//        searchDataList.add("若瑜欧尼呀");
//        searchDataList.add("抽抽子的日常");
//        searchDataList.add("lu.meng");
//        searchDataList.add("Cecelia");
//        searchDataList.add("小谭需要很努力");
//        searchDataList.add("瑶三岁dazzling");
//        searchDataList.add("米兰达 可儿");
//        searchDataList.add("ReykjavikkK");
//        searchDataList.add("韦韦韦欣言");
//        searchDataList.add("aaaaxbbb");
//        searchDataList.add("一七");
//        searchDataList.add("陈子蜜");
//        searchDataList.add("小川【美食测评】");
//        searchDataList.add("露露要飞");
//        searchDataList.add("未未weiwei");
//        searchDataList.add("Lelush 利路修");
//        searchDataList.add("周子清");
//        searchDataList.add("大玲玲");
//        searchDataList.add("AmandaX");
//        searchDataList.add("小来");
//        searchDataList.add("Chris姐姐_");
//        searchDataList.add("王玉萌");
//        searchDataList.add("pp桃子");
//        searchDataList.add("张饱饱zzz");
//        searchDataList.add("肉脸喵喵");
//        searchDataList.add("Kim Kardashian");
//        searchDataList.add("厉害的小红");
//        searchDataList.add("文司月");
//        searchDataList.add("演员王子奇");
//        searchDataList.add("Lee权哲");
//        searchDataList.add("李菲儿love");
//        searchDataList.add("演员马可");
//        searchDataList.add("刘惜君");
//        searchDataList.add("黄现喜Suzi de Givenchy");
//        searchDataList.add("刘芷微");
//        searchDataList.add("Nene郑乃馨");
//        searchDataList.add("有梦想的梦瑶");
//        searchDataList.add("老弟学穿搭");
//        searchDataList.add("姬天语 PeggyJi");
//        searchDataList.add("王可如Nina");
//        searchDataList.add("刘耀元");
//        searchDataList.add("钟金琪");
//        searchDataList.add("朱松玮Ray");
//        searchDataList.add("松本惠奈");
//        searchDataList.add("映玲");
//        searchDataList.add("连凯 andrew");
//        searchDataList.add("小虎牙欢欢");
//        searchDataList.add("袁冰妍");
//        searchDataList.add("张咸-Sa1t");
//        searchDataList.add("溫心");
//        searchDataList.add("李昕岳Sienna");
//        searchDataList.add("嘉容Christine");
//        searchDataList.add("马藜");
//        searchDataList.add("陈子由");
//        searchDataList.add("吴涟序Olivia");
//        searchDataList.add("Taylor Hill");
//        searchDataList.add("张雨甜甜");
//        searchDataList.add("路晨");
//        searchDataList.add("陆柯燃");
//        searchDataList.add("孙一杰");
//        searchDataList.add("彭必瑶");
//        searchDataList.add("辛芷蕾");
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
//                    Log.d("zunyu", "New Page: $className")
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
//                Log.d("zunyu", "-----------${baseSearchLog}：爬虫开始-------------")
//                searchListNode?.let { TraversalUtil.logTextInChildren(it) }
//                Log.d("zunyu", "-----------${baseSearchLog}：爬虫结束-------------")


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
                    if (avatar.parent != null) {
                        ClickUtil.pointClick(avatar.parent, service)
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
//                Log.d("zunyu", "-----------个人主页：爬虫开始-------------")
//                TraversalUtil.logTextInChildren(service.rootInActiveWindow)
//                Log.d("zunyu", "-----------个人主页：爬虫结束-------------")

                ClickUtil.performActionClickByText("更多", service)

                hander.postDelayed({
                    ClickUtil.boundClick(100, ScreenUtil.screenHeight!! - 100, service)
                    hander.postDelayed({
                        ClipboardUtil.setTopApp("com.subconscious.anotherme")
                        hander.postDelayed({
                            ClipboardUtil.getClipboardContent(service, curSearchContent)
                            searchDataList.removeAt(0)
                            ClipboardUtil.bringAppToForeground("com.xingin.xhs")
                        }, 500)
                    }, 1000)
                }, 1000)

            }

            "com.xingin.alioth.search.GlobalSearchActivity" -> {

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