package me.bambi.kinematics.listeners;

import me.bambi.kinematics.Kinematics;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EntityExplodeListener implements Listener {

    @EventHandler
    public void onEntityExplodeEvent(EntityExplodeEvent event) {
        if(Kinematics.settings.enable_protection) {
            int size = event.blockList().size();
            for(int i = 0; i < size; i++) {
                if(!Kinematics.settings.not_protected_materials.contains(event.blockList().get(i).getType())) {
                    event.blockList().remove(i);
                }
            }
        }


    }
}
