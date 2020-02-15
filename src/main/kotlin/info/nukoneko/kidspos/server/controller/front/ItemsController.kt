package info.nukoneko.kidspos.server.controller.front

import info.nukoneko.kidspos.server.controller.api.model.ItemBean
import info.nukoneko.kidspos.server.entity.ItemEntity
import info.nukoneko.kidspos.server.service.ItemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@Controller
@RequestMapping("/items")
class ItemsController {
    @Autowired
    private lateinit var itemService: ItemService

    @GetMapping
    fun index(model: Model): String {
        model.addAttribute("title", javaClass.simpleName)
        model.addAttribute("data", itemService.findAll())
        return "items/index"
    }

    @GetMapping("new")
    fun newItem(model: Model): String {
        model.addAttribute("item", ItemEntity())
        return "items/new"
    }

    @GetMapping("{id}/edit")
    fun edit(@PathVariable id: Int, model: Model): String {
        val item = itemService.findItem(id)
        model.addAttribute("item", item)
        return "items/edit"
    }

    @PostMapping
    fun create(@Valid @ModelAttribute item: ItemBean, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) return "items/new"
        itemService.save(item)
        return "redirect:/items"
    }

    @PutMapping("{id}")
    fun update(@PathVariable id: Int, @Valid @ModelAttribute item: ItemBean, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) return "items/edit"
        itemService.update(ItemEntity(id, item.barcode, item.name, item.price))
        return "redirect:/items"
    }
}