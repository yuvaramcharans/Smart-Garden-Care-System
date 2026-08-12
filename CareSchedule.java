package smartgarden;

public class CareSchedule 
{
    private String plantName;
    private String careType;
    private int daysSinceLastCare;
    private int careIntervalDays;
    private int priority;
    
    public CareSchedule(String plantName, String careType, int careIntervalDays, int priority) 
    {
        this.plantName = plantName;
        this.careType = careType;
        this.careIntervalDays = careIntervalDays;
        this.priority = priority;
        this.daysSinceLastCare = 0;
    }
    
    public CareSchedule(String plantName, String careType, int careIntervalDays) 
    {
        this(plantName, careType, careIntervalDays, 2);
    }
    
    public String getPlantName() 
    {
        return plantName;
    }
    
    public String getCareType() 
    {
        return careType;
    }
    
    public int getDaysSinceLastCare() 
    {
        return daysSinceLastCare;
    }
    
    public int getCareIntervalDays() 
    {
        return careIntervalDays;
    }
    
    public int getPriority() 
    {
        return priority;
    }
    
    public void incrementDays() 
    {
        this.daysSinceLastCare++;
    }
    
    public void resetDays() 
    {
        this.daysSinceLastCare = 0;
    }
    
    public boolean needsCare() 
    {
        return daysSinceLastCare >= careIntervalDays;
    }
    
    public boolean isOverdue() 
    {
        return daysSinceLastCare > careIntervalDays;
    }
    
    public String toString() 
    {
        String status = needsCare() ? "[NEEDS CARE]" : "[OK]";
        return status + " " + plantName + " - " + careType + " (Days: " + daysSinceLastCare + "/" + careIntervalDays + ", Priority: " + priority + ")";
    }
}
