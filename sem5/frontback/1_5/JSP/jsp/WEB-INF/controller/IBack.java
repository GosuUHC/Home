package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IBack {
    public void action(HttpServletRequest request, HttpServletResponse response);

}
