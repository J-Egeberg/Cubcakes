package com.cupcakes.webshop.web.controller;

import com.cupcakes.webshop.model.Bottom;
import com.cupcakes.webshop.model.CartLine;
import com.cupcakes.webshop.model.CupCake;
import com.cupcakes.webshop.model.Topping;
import com.cupcakes.webshop.service.CupCakeService;
import com.cupcakes.webshop.web.Color;
import com.cupcakes.webshop.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scheldejonas on 10/09/16.
 */
@Controller
public class CupCakeController {

    @Autowired
    private CupCakeService cupCakeService;

    // Shop page for displaying all Cup Cakes
    @RequestMapping("/")
    public String listCupCakes(Model model) {

        // Get all cupCakes for display in the shop
        List<CupCake> cupCakes = cupCakeService.findAll();

        // Add misc card attributes
        model.addAttribute("cupcakes", cupCakes);

        return "cupcake/index";
    }

    // Cup Cake image data
    @RequestMapping("/cupcake/topping/{cupCakeId}.png")
    @ResponseBody
    public byte[] toppingImage(@PathVariable Long cupCakeId) {

        // Return the byte array of image from Topping (only png's)
        return cupCakeService.findById(cupCakeId).getTopping().getImage();
    }

    // Cup Cake image data
    @RequestMapping("/cupcake/bottom/{cupCakeId}.png")
    @ResponseBody
    public byte[] bottomImage(@PathVariable Long cupCakeId) {

        // Return the byte array of image from Bottom (only png's)
        return cupCakeService.findById(cupCakeId).getBottom().getImage();
    }

    // Form for uploading a new Cup Cake
    @RequestMapping("/cupcake/upload")
    public String formNewCupCake(Model model) {

        if (!model.containsAttribute("cupcake")) {
            model.addAttribute("cupcake",new CupCake());
        }
        model.addAttribute("colors", Color.values());
        model.addAttribute("heading","Upload");
        model.addAttribute("action","/cupcake");
        model.addAttribute("submit","Upload Cup Cake");

        return "cupcake/form";
    }

    // Upload a new Cup Cake
    @RequestMapping(value = "/cupcake", method = RequestMethod.POST)
    public String addCupCake(CupCake cupCake, @RequestParam MultipartFile toppingfile, @RequestParam MultipartFile bottomfile, RedirectAttributes redirectAttributes) {
        // Upload new Cup Cake
        cupCakeService.save(cupCake,toppingfile,bottomfile);

        // Add flash message for succesfully upload
        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Your Cup Cake has been succesfully uploaded to the shop", FlashMessage.Status.SUCCES));

        // TODO: Redirect browser to new GIF's detail view
        return "redirect:/cupcake/upload";
    }

}
