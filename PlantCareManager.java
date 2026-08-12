package smartgarden;

import java.util.ArrayList;
import java.util.Iterator;

public class PlantCareManager 
{
    private ArrayList<CareSchedule> allSchedules;
    
    public PlantCareManager() 
    {
        allSchedules = new ArrayList<>();
    }
    
    public void addSchedule(CareSchedule schedule) 
    {
        allSchedules.add(schedule);
    }
    
    public int getTotalSchedules() 
    {
        return allSchedules.size();
    }
    
    public void displayAllSchedulesWithIterator() 
    {
        System.out.println("\n--- All Care Schedules ---");
        if (allSchedules.isEmpty()) 
        {
            System.out.println("No schedules available.");
        }
        else 
        {
            Iterator<CareSchedule> iterator = allSchedules.iterator();
            int count = 1;
            while (iterator.hasNext()) 
            {
                CareSchedule schedule = iterator.next();
                System.out.println(count + ". " + schedule);
                count++;
            }
        }
    }
    
    public void clearAllSchedules() 
    {
        allSchedules.clear();
    }
}
