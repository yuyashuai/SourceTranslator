package entity;

import java.util.List;

public class BingResponseEntity {
    /**
     * defs : [{"def":"词；单词；字；消息","pos":"n."},{"def":"措辞；用词","pos":"v."},{"def":"说得对","pos":"int."},{"def":"话；一个字；文字处理","pos":"Web"}]
     * sams : [{"chn":"但从未发现有什么地方用过\u2018时间\u2019这个词。","mp3Url":"https://dictionary.blob.core.chinacloudapi.cn/media/audio/tom/9b/a4/9BA4A2BB9C04C07E2F9BA968C63219D0.mp3","mp4Url":null,"eng":"I can state categorically that not once has the word time been used in the whole book."},{"chn":"这时寿生已经跑了进来，当真是一身泥，气喘喘地坐下了，说不出话来。","mp3Url":"https://dictionary.blob.core.chinacloudapi.cn/media/audio/tom/28/95/28959BB7208355BA345DC8505E2A9968.mp3","mp4Url":null,"eng":"By then Shousheng had already entered, truly covered with mud. The clerk sat down, panting for breath, unable to say a word."},{"chn":"Word文档或电子表格中的信息是静态的，修改起来比较麻烦（编辑、重新保存和发送修订后的文档）。","mp3Url":"https://dictionary.blob.core.chinacloudapi.cn/media/audio/tom/ca/2d/CA2D1D8EECE77F4C416EEC3D2D363C5E.mp3","mp4Url":null,"eng":"Writing information in a Word document or spreadsheet is static and is not nearly as easy to edit, resave, and send out revisions."},{"chn":"她就自动地介绍我去咖啡馆，销售样品，从\u201c中毒\u201d这个单词中受益。","mp3Url":"https://dictionary.blob.core.chinacloudapi.cn/media/audio/tom/66/a2/66A2247A2707EA4FC06E1F63676372D3.mp3","mp4Url":null,"eng":"She was responsible for introducing me to Cafe Bustelo, sample sales and the benefits of the word \"toxic. \""},{"chn":"我不懂这个词的词义，我要查一下字典。","mp3Url":"https://dictionary.blob.core.chinacloudapi.cn/media/audio/tom/34/f2/34F201108029051323DC6134D597EC66.mp3","mp4Url":"https://dictionary.blob.core.chinacloudapi.cn/media/video/cissy/34/f2/34F201108029051323DC6134D597EC66.mp4","eng":"I don't understand this word. I shall look it up in a dictionary."},{"chn":"那些你从我们的讯息中知道的，在你阅读的时候，你是能感觉每个单词中流露出的爱的能量的。","mp3Url":"https://dictionary.blob.core.chinacloudapi.cn/media/audio/tom/00/81/00813D2123DCE9FF6B7947483DE72308.mp3","mp4Url":null,"eng":"Those of you are familiar with our messages know otherwise, and that our positive energy carries with each word as you read them."},{"chn":"以上的毕业典礼串词，请各位英语高手帮忙翻译啊！","mp3Url":"https://dictionary.blob.core.chinacloudapi.cn/media/audio/tom/d2/44/D2447454EB826142DC7A8816B6880399.mp3","mp4Url":"https://dictionary.blob.core.chinacloudapi.cn/media/video/cissy/d2/44/D2447454EB826142DC7A8816B6880399.mp4","eng":"Over the graduation ceremony of the word string, to help you master English translation ah!"},{"chn":"当你这样做的时候狗儿可能感觉害怕。你不要说任何话。只是控制着他的头部几秒钟。","mp3Url":"https://dictionary.blob.core.chinacloudapi.cn/media/audio/tom/3e/79/3E79C6200282CEDFFA649CA5B2A51A35.mp3","mp4Url":null,"eng":"When you do this the dog will have a panic attack. You don't have to say one word here. Just hold his head for a few seconds."},{"chn":"\u201c我保证\u201d，宠物商店的销售员说，\u201c那个紫色的鹦鹉会重复它听见的每一个字。\u201d","mp3Url":"https://dictionary.blob.core.chinacloudapi.cn/media/audio/tom/54/1f/541F5D05FF3D3085D405D4401F1DC683.mp3","mp4Url":null,"eng":"I guarantee, \" said the salesman in the pet shop, \" that this purple parrot will repeat every word it hears."},{"chn":"不是要你跟他们讲说他们不是你的唯一，只要不要说是就好了。噢，还有要避开「爱」这个字。","mp3Url":"https://dictionary.blob.core.chinacloudapi.cn/media/audio/tom/a1/bd/A1BDCE63D782F6E247D2C1F49BAA47AB.mp3","mp4Url":null,"eng":"Not that you have to tell them that they are not the only one, just don't tell them they are. Oh, and avoid the word \"love. \""}]
     * pronunciation : {"BrE":"wɜː(r)d","AmE":"wɜrd","AmEmp3":"https://dictionary.blob.core.chinacloudapi.cn/media/audio/tom/23/91/2391C9DA9F78793E02D547EAF370A71A.mp3","BrEmp3":"https://dictionary.blob.core.chinacloudapi.cn/media/audio/george/23/91/2391C9DA9F78793E02D547EAF370A71A.mp3"}
     * word : word
     */
    public List<DefsEntity> defs;
    public List<SamsEntity> sams;
    public PronunciationEntity pronunciation;
    public String word;

    public class DefsEntity {
        /**
         * def : 词；单词；字；消息
         * pos : n.
         */
        public String def;
        public String pos;
    }

    public class SamsEntity {
        /**
         * chn : 但从未发现有什么地方用过‘时间’这个词。
         * mp3Url : https://dictionary.blob.core.chinacloudapi.cn/media/audio/tom/9b/a4/9BA4A2BB9C04C07E2F9BA968C63219D0.mp3
         * mp4Url : null
         * eng : I can state categorically that not once has the word time been used in the whole book.
         */
        public String chn;
        public String mp3Url;
        public String mp4Url;
        public String eng;
    }

    public class PronunciationEntity {
        /**
         * BrE : wɜː(r)d
         * AmE : wɜrd
         * AmEmp3 : https://dictionary.blob.core.chinacloudapi.cn/media/audio/tom/23/91/2391C9DA9F78793E02D547EAF370A71A.mp3
         * BrEmp3 : https://dictionary.blob.core.chinacloudapi.cn/media/audio/george/23/91/2391C9DA9F78793E02D547EAF370A71A.mp3
         */
        public String BrE;
        public String AmE;
        public String AmEmp3;
        public String BrEmp3;
    }
}
