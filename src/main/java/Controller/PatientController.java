package Controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.PatientDao;
import Model.Patient;
import Service.Servicess;

@WebServlet("/PatientController")
public class PatientController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PatientController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action.equalsIgnoreCase("Register")) {
			Patient p = new Patient();
			p.setFisrtName(request.getParameter("FirstName"));
			p.setLastName(request.getParameter("LastName"));
			p.setGender(request.getParameter("Gender"));
			p.setAddress(request.getParameter("Address"));
			p.setMobile(Long.parseLong(request.getParameter("Mobile")));
			p.setEmail(request.getParameter("Email"));
			p.setPassword(request.getParameter("Password"));

			PatientDao.insertPatient(p);
			request.setAttribute("msg", "Account Registered Succesfully");
			request.getRequestDispatcher("Patient-Login.jsp").forward(request, response);
		}

		else if (action.equalsIgnoreCase("Login")) {
			Patient p = new Patient();
			p.setEmail(request.getParameter("Email"));
			p.setPassword(request.getParameter("Password"));

			Patient p1 = PatientDao.loginPatient(p);
			if (p1 == null) {
				request.setAttribute("msg", "Password is Incorrect");
				request.getRequestDispatcher("Patient-Login.jsp").forward(request, response);
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("data", p1);
				request.getRequestDispatcher("Patient-Home.jsp").forward(request, response);
			}
		}

		else if (action.equalsIgnoreCase("Update")) {
			Patient p = new Patient();
			p.setID(Integer.parseInt(request.getParameter("ID")));
			p.setFisrtName(request.getParameter("FirstName"));
			p.setLastName(request.getParameter("LastName"));
			p.setGender(request.getParameter("Gender"));
			p.setAddress(request.getParameter("Address"));
			p.setMobile(Long.parseLong(request.getParameter("Mobile")));
			p.setEmail(request.getParameter("Email"));

			PatientDao.updateProfile(p);
			HttpSession session = request.getSession();
			session.setAttribute("data", p);
			request.getRequestDispatcher("Patient-Home.jsp").forward(request, response);
		}

		else if (action.equalsIgnoreCase("Change Password")) {
			Patient p = new Patient();
			int ID = Integer.parseInt(request.getParameter("ID"));
			String OP = request.getParameter("OP");
			String NP = request.getParameter("NP");
			String CNP = request.getParameter("CNP");

			boolean flag = PatientDao.checkOldPassword(ID, OP);

			if (flag == true) {
				if (NP.equals(CNP)) {
					PatientDao.changePassword(ID, NP);
					response.sendRedirect("Patient-Home.jsp");
				} else {
					request.setAttribute("msg1", "New Password and Confirm New Password Doesn't Matched.");
					request.getRequestDispatcher("Patient-Change-Password.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("msg", "Old Password Incorrect.");
				request.getRequestDispatcher("Patient-Change-Password.jsp").forward(request, response);
			}
		}

		else if (action.equalsIgnoreCase("GET OTP")) {
			String Email = request.getParameter("Email");
			boolean flag = PatientDao.checkEmail(Email);

			if (flag == true) {
				Servicess s = new Servicess();
				Random r = new Random();
				int num = r.nextInt(999999);
				System.out.println(num);
				s.sendMail(Email, num);

				request.setAttribute("Email", Email);
				request.setAttribute("OTP", num);
				request.getRequestDispatcher("Patient-Verify-OTP.jsp").forward(request, response);
			}
		}

		else if (action.equalsIgnoreCase("Verify")) {
			String Email = request.getParameter("Email");
			int OTP1 = Integer.parseInt(request.getParameter("OTP1"));
			int OTP2 = Integer.parseInt(request.getParameter("OTP2"));

			if (OTP1 == OTP2) {
				request.setAttribute("Email", Email);
				request.getRequestDispatcher("Patient-New-Password.jsp").forward(request, response);
			} else {
				request.setAttribute("Email", Email);
				request.setAttribute("OTP", OTP1);
				request.setAttribute("msg", "OTP Not Matched.");
				request.getRequestDispatcher("Patient-Verify-OTP.jsp").forward(request, response);
			}
		}

		else if (action.equalsIgnoreCase("Update Password")) {
			String Email = request.getParameter("Email");
			String NP = request.getParameter("NP");
			String CNP = request.getParameter("CNP");

			if (NP.equals(CNP)) {
				PatientDao.changeNewPassword(Email, NP);
				response.sendRedirect("Patient-Login.jsp");
			} else {
				request.setAttribute("msg", "New Pssword and Confirm New Password are Not matched.");
				request.setAttribute("Email", Email);
				request.getRequestDispatcher("Patient-New-Password.jsp").forward(request, response);
			}
		}
	}
}
