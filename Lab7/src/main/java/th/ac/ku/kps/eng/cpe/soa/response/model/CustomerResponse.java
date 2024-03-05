package th.ac.ku.kps.eng.cpe.soa.response.model;

import th.ac.ku.kps.eng.cpe.soa.model.Customer;

public class CustomerResponse {

	private String status;
	private Customer result;
	private String msg;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Customer getResult() {
		return result;
	}

	public void setResult(Customer result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
