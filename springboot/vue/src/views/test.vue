<template>
<div>
  <ul>
    <li v-for="item in items.slice(1)">
      {{item}}
    </li>
  </ul>

</div>


<!--  <div data-v-10fb71c3="" data-v-491ff6d4="" class="footer footer light-theme">-->
<!--    <div data-v-10fb71c3="" class="top">-->
<!--      <div data-v-10fb71c3="" class="navigation-bar-wrapper">-->
<!--        <ul data-v-10fb71c3="" class="navigation-bar four chinese">-->
<!--          <li data-v-10fb71c3="" class="navigation-item sign-up">用户协议</li>-->
<!--          <li data-v-10fb71c3="" class="navigation-item privacy">隐私政策</li>-->
<!--          <li data-v-10fb71c3="" class="navigation-item complaint">侵权投诉指引</li> &lt;!&ndash;&ndash;&gt;-->
<!--          <li data-v-10fb71c3="" class="navigation-item community">社区规范</li>-->
<!--        </ul>-->
<!--      </div>-->
<!--    </div>-->
<!--    <div data-v-10fb71c3="" class="bottom">-->
<!--      <div data-v-10fb71c3="" class="info"><span data-v-10fb71c3="" class="line"><a data-v-10fb71c3="" target="_blank"-->
<!--                                                                                    href="//beian.miit.gov.cn"-->
<!--                                                                                    title="小红书_沪ICP备">沪ICP备 13030189号 Copyright © 2014-2022 行吟信息科技（上海）有限公司</a> <span-->
<!--          data-v-10fb71c3="">| 地址：上海市黄浦区马当路388号C座</span> <span data-v-10fb71c3="">| 电话：021-64224530</span> <span-->
<!--          data-v-10fb71c3="">| <a data-v-10fb71c3="" target="_blank"-->
<!--                                  href="//dc.xhscdn.com/06c2adb0-b353-11e9-9d0c-7be9ff8961c1/自营经营者信息公示.pdf">自营经营者信息</a></span> <span-->
<!--          data-v-10fb71c3="">| <a data-v-10fb71c3="" target="_blank"-->
<!--                                  href="//www.beian.gov.cn/portal/registerSystemInfo?recordcode=31010102002533"-->
<!--                                  title="小红书_沪公网安备">2022  沪公网安备 31010102002533号<i data-v-10fb71c3="" class="police"></i></a></span></span>-->
<!--        <span data-v-10fb71c3="" class="line"><a data-v-10fb71c3="" target="_blank"-->
<!--                                                 href="//fe-video-qc.xhscdn.com/fe-platfrom/d0b7aeed1d933b011c2f75d0330714f17b046473.pdf"-->
<!--                                                 title="小红书_网络文化经营许可"> 网络文化经营许可证：沪网文［2021］1545-113号</a>-->
<!--          | <a data-v-10fb71c3="" target="_blank"-->
<!--               href="//fe-video-qc.xhscdn.com/picasso-editor/c081c205c927d605c959e3d6e1b22975c8a4b64f.pdf"-->
<!--               title="小红书_网文">增值电信业务经营许可证：沪B2-20150021</a>-->
<!--          | <a data-v-10fb71c3="" target="_blank" href="http://scjgj.sh.gov.cn/platform/survey/step1_phone"-->
<!--               title="小红书_网购评价"><i data-v-10fb71c3="" class="rank"></i> 网购(订餐)大家评</a></span> <span data-v-10fb71c3=""-->
<!--                                                                                                   class="line"><a-->
<!--            data-v-10fb71c3="" target="_blank" href="//o3.xiaohongshu.com/d/5-xingyin-sh-yiliaoqixie.pdf"-->
<!--            title="小红书_医疗器械经营">医疗器械经营许可证：沪嘉食药监械经营许20187006号</a>-->
<!--        | <a data-v-10fb71c3="" target="_blank"-->
<!--             href="https://ci.xiaohongshu.com/%E5%8C%BB%E7%96%97%E5%99%A8%E6%A2%B0%E7%BD%91%E7%BB%9C%E4%BA%A4%E6%98%93%E6%9C%8D%E5%8A%A1%E7%AC%AC%E4%B8%89%E6%96%B9%E5%B9%B3%E5%8F%B0%E5%A4%87%E6%A1%88.PDF"-->
<!--             title="小红书_医疗器械网络交易服务">医疗器械网络交易服务第三方平台备案:（沪）网械平台备字[2019]第00006号</a>-->
<!--        | <a data-v-10fb71c3="" target="_blank" href="//o3.xiaohongshu.com/d/3-xingyin-sh-yiyaoxinxi.pdf"-->
<!--             title="小红书_互联网药品信息服务">互联网药品信息服务资格证书：(沪)-经营性-2018-0011 </a></span> <span data-v-10fb71c3=""-->
<!--                                                                                     class="line"><span-->
<!--            data-v-10fb71c3="" title="小红书_违法不良信息">违法不良信息举报电话：(027) 8700 3878</span>-->
<!--        | <a data-v-10fb71c3="" target="_blank" href="http://www.shjbzx.cn" title="小红书_上海市互联网举报中心">上海市互联网举报中心</a>-->
<!--        | <a data-v-10fb71c3="" target="_blank" href="http://www.12377.cn" title="网上有害信息举报专区">网上有害信息举报专区</a></span>-->
<!--      </div>-->
<!--    </div>-->
<!--  </div>-->
</template>
<script>
export default {
  name: "Header",
  data() {
    return{
      items: ['1', '2', '3', '3','5']
    }

  }

}
</script>
<style scoped>
/*.footer[data-v-10fb71c3] {*/
/*  position: relative;*/
/*  font-family: PingFang SC, Helvetica, Arial, sans-serif;*/
/*  padding-top: 250px;*/
/*}*/

/*.light-theme[data-v-10fb71c3] {*/
/*  background-color: #fbfbfb;*/
/*}*/

/*a {*/
/*  text-decoration: none;*/
/*}*/

/*.top[data-v-10fb71c3] {*/
/*  font-size: 14px;*/
/*  font-weight: 500;*/
/*  text-align: center;*/
/*}*/

/*.top .navigation-bar-wrapper[data-v-10fb71c3] {*/
/*  width: 100%;*/
/*  display: flex;*/
/*  flex-direction: row;*/
/*  justify-content: center;*/
/*  align-items: center;*/
/*  margin: 0 0 20px;*/
/*}*/

/*.top .navigation-bar[data-v-10fb71c3] {*/
/*  width: 1080px;*/
/*  margin: 0;*/
/*  padding: 0 80px 0 60px;*/
/*  display: flex;*/
/*  flex-direction: row;*/
/*  justify-content: space-between;*/
/*  align-items: center;*/
/*  list-style-type: none;*/
/*  position: relative;*/
/*}*/

/*.bottom[data-v-10fb71c3] {*/
/*  width: 100%;*/
/*  height: 70px;*/
/*}*/

/*.bottom .info[data-v-10fb71c3] {*/
/*  display: flex;*/
/*  flex-direction: column;*/
/*  justify-content: space-between;*/
/*  align-items: center;*/
/*  width: 100%;*/
/*  height: 70px;*/
/*  font-size: 12px;*/
/*  line-height: 18px;*/
/*  font-weight: 400;*/
/*}*/

/*.bottom .info .line[data-v-10fb71c3] {*/
/*  display: flex;*/
/*  flex-direction: row;*/
/*  justify-content: center;*/
/*  align-items: center;*/
/*  white-space: nowrap;*/
/*}*/
</style>