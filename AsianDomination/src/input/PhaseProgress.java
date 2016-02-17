package input;

public class PhaseProgress {
	private int percentage;
	private String currentPhase;
	
	public PhaseProgress() {
		this.percentage = 0;
		this.currentPhase =  "";
	}
	
	public int getPercentage() {
		return percentage;
	}
	
	public void setPercentage(int percent) {
		this.percentage = percent;
	}

	public String getCurrentPhase() {
		return currentPhase;
	}
	
	public void setCurrentPhase(String phase) {
		this.currentPhase = phase;
	}
}
