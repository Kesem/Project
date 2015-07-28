package Services;

import javax.*;


@Path("/ComapnyService")
public class CompanyService
{
	private CompanyFacade company;
	
	@Context HttpServletRequest request;
	
	public CompanyService () {
		setCompany(new CompanyFacade());
	}
	
	public CompanyFacade getCompany() {
		return company;
	}

	public void setCompany(CompanyFacade company) {
		this.company = company;
	}
}
