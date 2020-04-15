package life.majiang.community.controller;

import life.majiang.community.cache.HotTagCache;
import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.service.NavService;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by codedrinker on 2019/4/24.
 */
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private HotTagCache hotTagCache;

    @GetMapping("/")
//    https://blog.csdn.net/yongwa123/article/details/85017551

    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "2") Integer size,
                        @RequestParam(name = "search", required = false) String search,
                        @RequestParam(name = "tag", required = false) String tag,
                        @RequestParam(name = "sort", required = false) String sort) {
//        返回问题和页码
        PaginationDTO pagination = questionService.list(search, tag, sort, page, size);
//         返回热门标签？？？？？？？？？？？？？？/？？？？？？？？？
        List<String> tags = hotTagCache.getHots();
        System.out.println(tags);

        model.addAttribute("pagination", pagination);
        model.addAttribute("search", search);
        model.addAttribute("tag", tag);
        model.addAttribute("tags", tags);
        model.addAttribute("sort", sort);
        return "index";
    }
}
