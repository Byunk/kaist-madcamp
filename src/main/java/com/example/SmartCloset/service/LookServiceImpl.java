package com.example.SmartCloset.service;

import com.example.SmartCloset.model.Inclination;
import com.example.SmartCloset.model.Look;
import com.example.SmartCloset.repository.LookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class LookServiceImpl implements LookService{

    private LookRepository lookRepository;

    @Autowired
    public LookServiceImpl(LookRepository lookRepository) {
        this.lookRepository = lookRepository;
    }

    @Override
    public HashMap<TPO, Float> getTPODistribution(ArrayList<Look> likeLooks) {
        HashMap<TPO, float> hashMap = new HashMap();
        int[] number = new int[6];
        int sum = 0;

        for(int i = 0; i < likeLooks.size(); i++){
            switch(likeLooks.get(i).getTpo()){
                case "DATE" : 
                    number[0]++;
                    sum++;
                    break;
                case "CAMPUS" : 
                    number[1]++;
                    sum++;
                    break;
                case "FORMAL" : 
                    number[2]++;
                    sum++;
                    break;
                case "CASUAL" : 
                    number[3]++;
                    sum++;
                    break;
                case "LOVELY" : 
                    number[4]++;
                    sum++;
                    break;
                case "DAILY" : 
                    number[5]++;
                    sum++;
                    break;
                default :
                    break;
            }
        }

        int i = 0;
        for(TPO tpo: TPO.values()) {
            float property = Math.round( number[i]/sum * 100 ) / 100.0;
            i++;
            hashMap.put(tpo.getTpo(), property)
        }

    }

    @Override
    public ArrayList<Look> getLooksByInclination(Inclination inclination, int count){
        //HashMap<TPO, Float> Tpodistribution = inclination.get(tpoDistribution);
        ArrayList<Look> tpolooks = new ArrayList();
        ArrayList<Look> resultlooks = new ArrayList();
        int tpocount;
        double random = Math.random();
        double random1 = Math.random();
        for( int i = 0; i < count; i++){
            String like_tpo = getcaseByInclination(inclination, random)
            tpolooks = getLookByTpo(like_tpo);
            tpocount = getLooksCountByTpo(like_tpo);
            resultlooks.add(tpolooks.get((int)tpocount*tpocount));    
        }
    }

    @Override
    public ArrayList<Look> getLooksById(ArrayList<String> ids){
        ArrayList<Look> looks = new ArrayList();
        for(int i = 0; i < ids.size(); i++){
            looks.add(getLookByTpo(ids.get(i)));
        }
    }

    @Override
    public String getcaseByInclination(Inclination inclination, double property){
        HashMap<Tpo, Float> tpodistribution = inclination.getTPODistribution();
        double start = 0;
        for(TPO tpo: TPO.values()) {
            String present_tpo = tpo.getTPO();
            if(start <= prorpty && proprtry <tpodistribution.get(present_tpo)){
                return present_tpo;
            }
            else{
                start = start + tpodistribution.get(present_tpo);
            }
        }
        return DAILY;
    }    
    
    @Override
    public Look getLookById(String id) {
        return lookRepository.findById(id).orElse(null);
    }

    @Override
    public Look saveOrUpdate(Look look) {
        return lookRepository.insert(look);
    }

    @Override
    public void delete(String id) {
        lookRepository.deleteById(id);
    }

}
