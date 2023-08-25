package com.example.twisterclient.services;

import com.example.twisterclient.models.TypesItem;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class TypesItemService {
    public void load(Model model) {
        for (var val : TypesItem.values()) {
            model.addAttribute(val + "Item", val);
        }
    }
}
