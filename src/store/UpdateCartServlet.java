package store;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateCartServlet
 */
public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCartServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("cart.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
		ArrayList<String> toRemove = new ArrayList<String>();
		Iterator<String> iter = cart.getIterator();
		while(iter.hasNext()) {
			String id = iter.next();
			int numProducts = Integer.parseInt(request.getParameter(id));
			if(numProducts==0) {
				toRemove.add(id);
			} else if(numProducts >0) {
				cart.setProduct(id, numProducts);
			}
		}
		for(int i=0 ; i<toRemove.size(); i++) {
			cart.removeProduct(toRemove.get(i));
		}
		request.getRequestDispatcher("/QuizDescriptionServlet").forward(request,response);
	}

}
