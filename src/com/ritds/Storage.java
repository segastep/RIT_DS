package com.ritds;

import java.util.*;

public class Storage {

    private Map<Integer, ArrayList<Observation>> map = new HashMap<>();

    public Map<Integer, ArrayList<Observation>> returnMap() {
        return map;
    }

    public String create(int id, long timestamp, String data) {
        if (this.map.containsKey(id)) return "ERR A history already exists for identifier '" + id + "'";
        else {
            ArrayList<Observation> temp = new ArrayList<>();
            temp.add(new Observation(timestamp, data));
            map.put(id, temp);
            return "OK " + data;
        }
    }

    public String update(int id, long timestamp, String data) {
        if(map.containsKey(id)) {
            String priorData = priorAsOfObservation(id, timestamp).getData();
            ArrayList<Observation> observations = map.get(id);
            Observation observation = new Observation(timestamp, data);
            if (observations.contains(observation)) {
                observations.set(observations.indexOf(observation), observation); // if observation with this timestamp exists - replace it
            }else {
                observations.add(observation);
            }
            map.put(id, observations); // update identifier history with new observation
            return "OK " + priorData;
        }else {
            return "ERR No history exists for identifier '" + id + "'";
        }
    }

    public String delete(int id, long timestamp) {

        if (map.containsKey(id)) {
        ArrayList<Observation> observations = map.get(id);
        Observation asOf = priorAsOfObservation(id, timestamp);
        observations.removeIf((Observation o )-> o.getTimestamp() >= timestamp);
        if (!observations.isEmpty()){
        map.put(id, observations);
        return "OK " + asOf.getData();
        }
        return "ERR no history exists for identifier '" + id + "'";
        }
        return "ERR no such identifier '"  + id + "'";

    }

    public String delete(int id){
        if (map.containsKey(id)) {
            Observation greatest = Collections.max(map.get(id));
            map.remove(id);
            return "OK " + greatest.getData();
        }
        return "ERR no such identifier '" + id + "'";

    }

    public String get(int id, long timestamp) {
        if (map.containsKey(id)) {
            return "OK " + priorAsOfObservation(id, timestamp).getData();
        }else {
            return "ERR No history exists for identifier '" + id + "'";
        }
    }

    public String latest(int id) {
        if (map.containsKey(id)) {
            Observation latest = Collections.max(map.get(id));
            return "OK " + latest.getTimestamp() + " " + latest.getData();
        }else {
            return "ERR No history exists for identifier '" + id + "'";
        }
    }

    private Observation priorAsOfObservation(int id, long timestamp) {
        ArrayList<Observation> observations = map.get(id);
        Observation temp = new Observation(timestamp, "");
        if (observations.contains(temp)) return observations.get(observations.indexOf(temp));
        else {
            // find as-of
            Observation last = observations.get(0);
            for(Observation o: observations) {
                if(o.getTimestamp() > timestamp) break;
                last = o;
            }
            return last;
        }
    }

}