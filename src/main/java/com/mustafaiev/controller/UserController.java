package com.mustafaiev.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.mustafaiev.domain.Address;
import com.mustafaiev.dto.AddressDTO;
import com.mustafaiev.dto.UserDTO;
import org.apache.log4j.Logger;
import com.mustafaiev.domain.User;
import com.mustafaiev.service.UserService;
import com.mustafaiev.service.AddressService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Handles and retrieves address request
 *
 * @author Mustafaiev
 */
@Controller
@RequestMapping("/user")
public class UserController {

    protected static Logger logger = Logger.getLogger("controller");

    @Resource(name = "addressService")
    private AddressService addressService;

    @Resource(name = "userService")
    private UserService userService;

    /**
     * Sets the DataTime format
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
    }


    /**
     * Retrieves the Users page
     *
     * @param model
     * @param req   gets parameters entered into the search box
     * @param resp  sends parametes entered into the search box back to the view
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getRecords(Model model, HttpServletRequest req, HttpServletRequest resp) {

        logger.debug("Received request to show posts page");

        String firstName = paramValidation("firstName", req);
        String lastName = paramValidation("lastName", req);
        String email = paramValidation("email", req);


        resp.setAttribute("firstName", firstName);
        resp.setAttribute("lastName", lastName);
        resp.setAttribute("email", email);


        List<UserDTO> userListDTO = getUserListDTO(firstName, lastName, email);

        // Add to model
        model.addAttribute("userList", userListDTO);

        // This will resolve to /WEB-INF/jsp/user-list.jsp
        return "user-list";
    }

    /**
     * Gets the list of addresses
     *
     * @param firstName requested firstName
     * @param lastName  requested lastName
     * @param email     requested email
     * @return list of users
     */
    public List getUserListDTO(String firstName, String lastName, String email) {

        List<UserDTO> userListDTO = new ArrayList<UserDTO>();

        List<User> users;

        if (firstName != null && lastName != null && email != null) {
            users = userService.getSearchedList(firstName, lastName, email);
        } else {
            users = userService.getAll();
        }


        for (User user : users) {
            // Create new data transfer object
            userListDTO.add(getUserDTO(user));

        }
        return userListDTO;
    }


    /**
     * Gets user data transfer object
     *
     * @param user
     * @return
     */
    public UserDTO getUserDTO(User user) {

        UserDTO dto = new UserDTO();

        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPatronymic(user.getPatronymic());
        dto.setPhone(user.getPhone());
        dto.setEmail(user.getEmail());
        dto.setBirthDate(user.getBirthDate());

        return dto;

    }

    /**
     * Checks if the parameter is null
     *
     * @param param
     * @param req
     * @return parameter or empty field instead of null
     */
    public String paramValidation(String param, HttpServletRequest req) {

        String value;
        if ((req.getParameter(param) != null)) {
            value = (req.getParameter(param));
        } else {
            value = "";
        }
        return value;
    }

    /**
     * Retrieves the "Add New User" page
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAdd(Model model) {
        logger.debug("Received request to show add page");

        // Prepare model object
        List<AddressDTO> addressListDTO = getAddressListDTO();

        // Add to model
        model.addAttribute("addressList", addressListDTO);

        // Prepare model object
        User user = new User();

        model.addAttribute("userAttribute", user);

        return "user-new";
    }

    /**
     * Adds new user
     *
     * @param user
     * @param result
     * @param req    gets the address Id
     * @param model
     * @return redirects to user list page
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String postAdd(@Valid @ModelAttribute("userAttribute") User user, BindingResult result, HttpServletRequest req, Model model) {
        logger.debug("Received request to add new user");

        if (result.hasErrors()) {

            List<AddressDTO> addressListDTO = getAddressListDTO();

            model.addAttribute("addressList", addressListDTO);

            model.addAttribute("userAttribute", user);

            return "user-new";
        }

        String addressId = req.getParameter("addressId");

        if (addressId == "") {
            userService.add(user);

            return "redirect:/main/user/list";
        }
        try {
            userService.add(Integer.parseInt(req.getParameter("addressId")), user);

            return "redirect:/main/user/list";
        } catch (Exception ex) {

            return "redirect:/main/user/list";
        }
    }

    /**
     * Gets the list of addresses
     */
    public List getAddressListDTO() {

        List<AddressDTO> addressListDTO = new ArrayList<AddressDTO>();

        List<Address> addresses = addressService.getAll();

        // Add to model list

        for (Address address : addresses) {
            // Create new data transfer object
            addressListDTO.add(getAddressDTO(address));

        }
        return addressListDTO;
    }

    /**
     * Gets the address dto
     *
     * @param address
     * @return data transfer object
     */
    public AddressDTO getAddressDTO(Address address) {

        AddressDTO dto = new AddressDTO();

        dto.setId(address.getId());
        dto.setRegion(address.getRegion());
        dto.setCity(address.getCity());
        dto.setStreet(address.getStreet());
        dto.setHomeNumber(address.getHomeNumber());
        dto.setFlatNumber(address.getFlatNumber());

        return dto;
    }

    /**
     * Deletes a user
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String getDelete(@RequestParam("id") Integer userId) {
        logger.debug("Received request to delete credit card");

        // Delegate to service
        userService.delete(userId);

        // Redirect to url
        return "redirect:/main/user/list";
    }

    /**
     * Retrieves the "Add New User" page
     *
     * @param userId
     * @param model
     * @param resp
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEdit(@RequestParam("id") Integer userId, Model model, HttpServletRequest resp) {
        logger.debug("Received request to show edit page");

        // Prepare model object
        List<AddressDTO> addressListDTO = getAddressListDTO();

        // Add to model
        model.addAttribute("addressList", addressListDTO);

        // Retrieve user by id
        User existingUser = userService.get(userId);

        Address userAddress = existingUser.getAddress();
        if (existingUser.getAddress() != null) {
            resp.setAttribute("addressId", userAddress.getId());
        }

        // Add to model
        model.addAttribute("userAttribute", existingUser);

        // This will resolve to /WEB-INF/jsp/user-edit.jsp
        return "user-edit";
    }

    /**
     * Adds new user
     * @param userId
     * @param user
     * @param result
     * @param model
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postEdit(@RequestParam("id") Integer userId, @Valid
    @ModelAttribute("userAttribute") User user, BindingResult result, Model model, HttpServletRequest req, HttpServletRequest resp) {
        logger.debug("Received request to add new user");

        if (result.hasErrors()) {

            List<AddressDTO> addressListDTO = getAddressListDTO();

            User existingUser = userService.get(userId);

            Address userAddress = existingUser.getAddress();

            if (existingUser.getAddress() != null) {
                resp.setAttribute("addressId", userAddress.getId());
            }

            model.addAttribute("addressList", addressListDTO);
            model.addAttribute("userAttribute", user);

            return "user-edit";
        }

        String addressId = req.getParameter("addressId");

        if (addressId == "") {
            userService.edit(user);

            return "redirect:/main/user/list";
        }
        try {
            userService.edit(Integer.parseInt(req.getParameter("addressId")), user);

            return "redirect:/main/user/list";
        } catch (Exception ex) {

            return "redirect:/main/user/list";
        }
    }
}