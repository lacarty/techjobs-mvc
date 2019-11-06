package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results")
    public String search(Model model, @RequestParam String searchColumn, @RequestParam String searchTerm) {

        // call findByValue() in JobData
        // search ALL columns for the specified searchTerm
        // display jobs that match
        if (searchColumn.equals("all")) {
            ArrayList<HashMap<String, String>> jobsByColumnAndValue = JobData.findByValue(searchTerm);
            model.addAttribute("jobList", jobsByColumnAndValue);
            model.addAttribute("columns", ListController.columnChoices);
            return "search";

            // call findByColumnAndValue() in JobData
            // search specified column for specified search term
        } else {
            ArrayList<HashMap<String, String>> jobsByColumnAndValue = JobData.findByColumnAndValue(searchColumn, searchTerm);
            model.addAttribute("jobList", jobsByColumnAndValue);
            model.addAttribute("columns", ListController.columnChoices);
            return "search";
        }
    }
}
