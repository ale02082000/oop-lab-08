package it.unibo.deathnote.api.impl;
import java.util.LinkedHashMap;
import java.util.Map;


import it.unibo.deathnote.api.DeathNote;

public class DeathNodeImpl implements DeathNote{

Map<String, String> human  = new LinkedHashMap<>();
Map<String, String> mapForDetails  = new LinkedHashMap<>();
protected long timeNameWritten;
protected long timeCauseWritten;
String lastNameWritten;

@Override 
public String getRule(final int ruleNumber){
    if(ruleNumber < 1 || ruleNumber > RULES.size()){
        throw new IllegalArgumentException("Number of rules aren't correct");
    }
    return RULES.get(ruleNumber - 1);
}

@Override
public void writeName(final String name) {
    if (name == null) {
        throw new NullPointerException("The name is null");
    }
    lastNameWritten = name;
    human.put(name, "heart attack");
    mapForDetails.put(name, "");
    this.timeNameWritten = System.currentTimeMillis();
    this.timeCauseWritten = this.timeNameWritten; // riferimento iniziale per i dettagli
}


@Override 
public boolean writeDeathCause(final String cause){
    if(cause == null || lastNameWritten == null){
        throw new IllegalStateException("The cause is null");
    }

    final long timeToGetCause = System.currentTimeMillis();
    final long elapsedTime = timeToGetCause - this.timeNameWritten;

    if(elapsedTime <= 40){
        human.put(this.lastNameWritten, cause);
        timeCauseWritten = System.currentTimeMillis();
        return true;
    }
        return false;
}

@Override 
public boolean writeDetails(final String details){
    if(this.lastNameWritten == null || details == null){
        throw new IllegalStateException("the name or the details are null");
    }
    final long timeToGetDetails = System.currentTimeMillis();
    final long elapsedTIme1 = timeToGetDetails - timeCauseWritten;

    if(elapsedTIme1 <  6040){
        mapForDetails.put(lastNameWritten, details);
        return true;
    }
    return false;
}

@Override 
public String getDeathCause(final String name){
    if(!human.containsKey(name)){
        throw new IllegalArgumentException("The name isn't present in DeathNote");
    }
        return human.get(name);
}

@Override 
public String getDeathDetails(final String name){
    if(!human.containsKey(name)){
        throw new IllegalArgumentException("The name isn't present in DeathNote");
    }
    return mapForDetails.get(name);
}

@Override 
public boolean isNameWritten(final String name){
    if(human.containsKey(name)){
        return true;
    }
    return false;
    }
}
