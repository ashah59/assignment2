package Admin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Business.Product;
import Data.ProductDB;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shah
 */
public class ProdMaint extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //ProductIO.init(getServletContext().getRealPath("/WEB-INF/products.txt"));
        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "home";  // default action
        }

        // perform action and set URL to appropriate page
        String url = "/index.jsp";
        if (action.equals("home")) {

            url = "/index.jsp";

        } else if (action.equals("displayProd")) {

            url = "/products.jsp";
            ArrayList<Product> products = ProductDB.selectProducts();
            request.setAttribute("products", products);

        } else if (action.equals("addProd")) {

            url = "/product.jsp";
            String prodId = request.getParameter("prodId");
            if (prodId != null && !prodId.isEmpty()) {
                Product prod = ProductDB.selectProduct(Long.parseLong(prodId));
                request.setAttribute("product", prod);
            }

        } else if (action.equals("deleteProd")) {

            url = "/confirm_product_delete.jsp";
            String prodId = request.getParameter("prodId");
            Product prod = ProductDB.selectProduct(Long.parseLong(prodId));
            request.setAttribute("product", prod);

        } else if (action.equals("confDelProd")) {

            String prodId = request.getParameter("prodId");
            Product prod = ProductDB.selectProduct(Long.parseLong(prodId));
            ProductDB.delete(prod);

            url = "/products.jsp";
            ArrayList<Product> products = ProductDB.selectProducts();
            request.setAttribute("products", products);

        } else if (action.equals("updateProd")) {

            String code = request.getParameter("code");
            String desc = request.getParameter("desc");
            String price = request.getParameter("price");
            String prodId = request.getParameter("prodId");

            Product prod = new Product();
            prod.setCode(code);
            prod.setDescription(desc);
            if (price != null && !price.isEmpty()) {
                try {
                    double priceValue = Double.parseDouble(price);
                    if (priceValue >= 0.0) {
                        prod.setPrice(priceValue);
                    } else {
                        price = "";
                    }
                } catch (NumberFormatException e) {
                    price = "";
                }
            }

            if (code != null && !code.isEmpty() && desc != null && !desc.isEmpty() && price != null && !price.isEmpty()) {
                url = "/products.jsp";
                if (prodId != null && !prodId.isEmpty()) {
                    //Update
                    prod.setId(Long.parseLong(prodId));
                    ProductDB.update(prod);
                } else {
                    //Add
                    ProductDB.insert(prod);
                }

                ArrayList<Product> products = ProductDB.selectProducts();
                request.setAttribute("products", products);
            } else {
                url = "/product.jsp";
                request.setAttribute("message", "Please enter valid values in all three text boxes.");
                if (prodId != null && !prodId.isEmpty()) {
                    prod.setId(Long.parseLong(prodId));
                }
                request.setAttribute("product", prod);
            }
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
