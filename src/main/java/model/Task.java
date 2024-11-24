package model;

public class Task {
	  private int id;
	  private int userId;
	  private String inputData;
	  private String status;
	  private String result;
	  
	public Task(int id, int userId, String inputData, String status, String result) {
		super();
		this.id = id;
		this.userId = userId;
		this.inputData = inputData;
		this.status = status;
		this.result = result;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getInputData() {
		return inputData;
	}

	public void setInputData(String inputData) {
		this.inputData = inputData;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", userId=" + userId + ", inputData=" + inputData + ", status=" + status + ", result="
				+ result + "]";
	}
}
