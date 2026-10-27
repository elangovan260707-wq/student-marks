
package com.student;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/MarksServlet")
public class MarksServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try {
            String name = request.getParameter("studentName");

            int m1 = Integer.parseInt(request.getParameter("mark1"));
            int m2 = Integer.parseInt(request.getParameter("mark2"));
            int m3 = Integer.parseInt(request.getParameter("mark3"));

            if (name == null || name.trim().isEmpty()
                    || m1 < 0 || m1 > 100
                    || m2 < 0 || m2 > 100
                    || m3 < 0 || m3 > 100) {

                response.getWriter().println(
                    "Invalid input! Enter a name and marks from 0 to 100.");
                return;
            }

            int total = m1 + m2 + m3;
            double average = total / 3.0;

            String grade;

            if (average >= 90) {
                grade = "A";
            } else if (average >= 75) {
                grade = "B";
            } else if (average >= 60) {
                grade = "C";
            } else if (average >= 40) {
                grade = "D";
            } else {
                grade = "Fail";
            }

            request.setAttribute("studentName", name);
            request.setAttribute("total", total);
            request.setAttribute("average", average);
            request.setAttribute("grade", grade);

            request.getRequestDispatcher("/result.jsp")
                   .forward(request, response);

        } catch (NumberFormatException e) {
            response.getWriter().println(
                "Invalid marks! Please enter numbers from 0 to 100.");
        }
    }
}
