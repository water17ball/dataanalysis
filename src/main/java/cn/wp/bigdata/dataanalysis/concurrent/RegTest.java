package cn.wp.bigdata.dataanalysis.concurrent;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import java.util.Arrays;

public class RegTest {
    public static void main(String[] args) {
        String content = "<p>提升我国产业链现代化水平，要准确把握新发展阶段的新机遇新挑战，立足以畅通国民经济循环为目标方向构建新发展格局，着力深化供给侧结构性改革，优化产业链体系、提高产业链供应链安全可控水平。</p><p><b>一要进一步扩大内需尤其是消费需求，发挥大国经济超大规模市场需求的巨大优势，拉动产业链体系优化。</b>我国经济发展总体上已迈入上中等收入阶段，依靠大规模投资拉动的发展阶段已过，传统重化工业产能过剩，可廉价复制的创新存量少了，找到更好的生产性投资项目更不容易，还要防止虚拟经济加速膨胀驱使国民经济虚高度化。必须把促进和扩大居民消费放在优先位置，推动消费需求结构升级，依托最终需求扩大来拉动产业链体系优化。消费需求是最稳定的永恒的，把14亿中国人民生活搞好是最大的事。随着出口导向型和投资拉动型经济增长渐趋放缓，国内消费驱动型经济的潜力将得到更大发挥，更强劲、可持续、多样化的消费需求，将拉动产业结构、供给结构重组、转型、升级，推动新技术应用，带动产业链新一轮优化升级。我国居民消费占比远低于人均GDP相近的国家，要逐渐提高居民收入在国民收入中比例，提高劳动收入在居民收入分配中比重，更加注重分配公平、防止收入分配两极分化，稳步提高消费占GDP的份额，促进居民消费结构不断优化升级，让更多人过上更健康、更多样、更好的生活。大规模市场需求优势是人口大国最大优势，十几亿人民消费需求升级，是拉动以国内大循环为主、国际国内双循环相互促进的新发展格局的引擎。</p>";
        Document document = Jsoup.parse(content);
        Elements elements = document.getElementsByTag("p");
        for (Element element : elements) {
            if(StringUtils.isEmpty(element.text())){

            }
        }
    }
}
