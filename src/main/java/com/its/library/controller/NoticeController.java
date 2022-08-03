package com.its.library.controller;

import com.its.library.dto.NoticeDTO;
import com.its.library.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {
    private final NoticeService noticeService;
    @GetMapping("/history/{id}")
    public String noticeHistory(@PathVariable("id")Long memberId, Model model){
       List<NoticeDTO> noticeDTOList = noticeService.noticeHistory(memberId);
        model.addAttribute("noticeList",noticeDTOList);
        return "notice/noticeHistory";
    }
    @GetMapping("/readCount/{id}")
    public @ResponseBody boolean noticeReadCount(@PathVariable("id")Long id){
       boolean result = noticeService.readFalseCount(id);
       return result;

    }
}