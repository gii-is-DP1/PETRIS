package org.springframework.samples.petclinic.achievements;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;


public class AchievementFormatter implements Formatter<Achievement> {
    private AchievementService achievementService;

    @Autowired
    public AchievementFormatter(AchievementService as){achievementService=as;}

    @Override
    public String print(Achievement object, Locale locale) {        
        return object!=null?object.getName():"";
    }

    @Override
    public Achievement parse(String text, Locale locale) throws ParseException {
        Achievement result=null;
        result=achievementService.getAchievementByName(text);
        if(result==null)
            throw new ParseException("Achievement with name '"+text+"' not found!", 0);
        return result;
    }
}
