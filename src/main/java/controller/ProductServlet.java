package controller;

import dao.category.CategoryDao;
import model.Category;
import model.Product;
import service.product.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/product")
public class ProductServlet extends HttpServlet {
    public static  final ProductService PRODUCT_SERVICE = new ProductService();
    public static final String ACTION = "action";
    public static final String EMPTY = "";
    public static final String CREATE = "create";
    public static final CategoryDao CATEGORY_DAO = new CategoryDao();
    public static final String EDIT = "edit";
    public static final String DELETE = "delete";
    public static final String MESSAGE = "message";
    public static final String SEARCH = "search";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter(ACTION);
        if (action == null) {
            action = EMPTY;
        }
        switch (action) {
            case CREATE:{
                formCreateProduct(request, response);
                break;
            }
            case EDIT:{
                formEditInfo(request, response);
                break;
            }
            case DELETE:{
                deleteProduct(request, response);
                break;
            }
            default: {
                showAllProduct(request, response);
                break;
            }
        }
    }

    private void searchProduct(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("search");
        List<Product> products =  null;
        if (name == null || name.equals("")) {
            products = PRODUCT_SERVICE.getAll();
        } else {
            products = PRODUCT_SERVICE.findProductName(name);
        }

        for (Product product: products) {
            int categoryId = product.getCategoryId();
            Category category = CATEGORY_DAO.findCategory(categoryId);
            product.setCategory(category);
        }
        request.setAttribute("products", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/list.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean isDelete = PRODUCT_SERVICE.delete(id);
        try {
            response.sendRedirect("product");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void formEditInfo(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = PRODUCT_SERVICE.findProductById(id);
        request.setAttribute("product", product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/edit.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void formCreateProduct(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/create.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }

    private void showAllProduct(HttpServletRequest request, HttpServletResponse response) {
        List<Product> products = PRODUCT_SERVICE.getAll();
        for (Product product: products) {
            int categoryId = product.getCategoryId();
            Category category = CATEGORY_DAO.findCategory(categoryId);
            product.setCategory(category);
        }
        request.setAttribute("products", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/list.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter(ACTION);
        switch (action) {
            case CREATE:{
                createProduct(request, response);
                break;
            }
            case EDIT:{
                editProduct(request, response);
                break;
            }
            case SEARCH: {
                searchProduct(request, response);
                break;
            }
        }
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = getProduct(request);
        product.setId(id);
        boolean isSave = PRODUCT_SERVICE.edit(id,product);
        if (isSave) {
            request.setAttribute(MESSAGE, "Product was updated");
        } else {
            request.setAttribute(MESSAGE, "Product can not updated");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/edit.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) {
        Product product = getProduct(request);
        boolean isSave = PRODUCT_SERVICE.save(product);
        if (isSave) {
            request.setAttribute(MESSAGE, "Product was created");
        } else {
            request.setAttribute(MESSAGE, "Product can not created");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/create.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private Product getProduct(HttpServletRequest request) {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        int categoryId = Integer.parseInt(request.getParameter("category"));

        return new Product(name, price, quantity, color, description, categoryId);
    }
}
