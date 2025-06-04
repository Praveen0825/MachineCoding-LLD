package service;

import entity.Agent;
import exceptions.AgentNotFoundException;
import exceptions.OrderNotFoundException;

import java.util.*;

public class AgentService {
    private static volatile AgentService instance;
    private Map<String, Agent> agents= new LinkedHashMap<>();
    private AgentService(){}

    public static AgentService getInstance(){
        if(instance==null){
            synchronized (AgentService.class){
                if(instance==null){
                    instance=new AgentService();
                }
            }
        }
        return instance;
    }

   public void createAgent(String name,int pincode){
        agents.put(name,new Agent(name,pincode));
   }

   public Agent getAgent(String name) throws AgentNotFoundException {
        if(!agents.containsKey(name))
           throw new AgentNotFoundException("invalid order name");
        return agents.get(name);
   }

   public Collection<Agent> getAllAgents(){
        return agents.values();
   }

}
