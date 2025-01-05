package com.fahmy.sprinai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HelpDeskChatbotViewController {

    @Autowired
    private HelpDeskChatbotAgentService chatbotService;

    @GetMapping("/chat")
    public String chatPage(@RequestParam(required = false) String historyId, Model model) {
        if (historyId != null) {
            List<HistoryEntry> history = chatbotService.getConversationalHistoryById(historyId);
            model.addAttribute("history", history);
        }
        return "chat";
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String userMessage, @RequestParam String historyId, Model model) {
        String response = chatbotService.call(userMessage, historyId);
        model.addAttribute("response", response);
        return "redirect:/chat?historyId=" + historyId;
    }
}
