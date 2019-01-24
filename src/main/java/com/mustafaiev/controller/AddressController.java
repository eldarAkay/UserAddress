package com.mustafaiev.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.mustafaiev.domain.User;
import com.mustafaiev.dto.AddressDTO;
import org.apache.log4j.Logger;
import com.mustafaiev.domain.Address;
import com.mustafaiev.service.UserService;
import com.mustafaiev.service.AddressService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles and retrieves Address request
 *
 * @author Eldar Mustafaiev
 */
@Controller
@RequestMapping("/address")
public class AddressController {

    protected static Logger logger = Logger.getLogger("controller");

    @Resource(name = "addressService")
    private AddressService addressService;

    @Resource(name = "userService")
    private UserService userService;

    /**
     * Retrieves the Addresses page
     *
     * @param req  gets parameters entered into the search box
     * @param resp sends parametes entered into the search box back to the view
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getRecords(Model model, HttpServletRequest req, HttpServletRequest resp) {
        logger.debug("Received request to show addresses page");

        String region = paramValidation("region", req);
        String city = paramValidation("city", req);
        String street = paramValidation("street", req);

        resp.setAttribute("region", region);
        resp.setAttribute("city", city);
        resp.setAttribute("street", street);

        List<AddressDTO> addressListDTO = getAddressListDTO(region, city, street);

        model.addAttribute("addressList", addressListDTO);

        return "address-list";
    }

    /**
     * Gets the list of addresses
     *
     * @param region requested region
     * @param city   requested city
     * @param street requested street
     * @return list of addresses
     */
    public List getAddressListDTO(String region, String city, String street) {
        List<AddressDTO> addressListDTO = new ArrayList<AddressDTO>();
        List<Address> addresses;

        if (region != null && city != null && street != null) {
            addresses = addressService.getSearchedList(region, city, street);
        } else {
            addresses = addressService.getAll();
        }

        for (Address address : addresses) {
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
     * Retrieves the "Add New Address" page
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAdd(Model model) {
        logger.debug("Received request to show add page");

        Address address = new Address();
        model.addAttribute("addressAttribute", address);

        return "address-new";
    }

    /**
     * Adds new address
     *
     * @param address
     * @param result  checks if there is an error in form input
     * @param model
     * @return if result has error - returns new address page
     * and address-list page if form has no errors
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String postAdd(@Valid @ModelAttribute("addressAttribute") Address address, BindingResult result, Model model) {
        logger.debug("Received request to add new record");

        if (result.hasErrors()) {
            model.addAttribute("addressAttribute", address);

            return "address-new";
        }

        addressService.add(address);

        return "redirect:/main/address/list";
    }

    /**
     * Deletes the address and sets address column of user with this address to null
     *
     * @param addressId id field of address object
     * @return redirects to address list page
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String getDelete(@RequestParam("id") Integer addressId) {
        logger.debug("Received request to delete address");
        updateUsers(addressId);
        addressService.delete(addressId);

        return "redirect:/main/address/list";
    }

    /**
     * @param addressId
     * @param model
     * @return address edit view page
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEdit(@RequestParam("id") Integer addressId, Model model) {
        logger.debug("Received request to show edit page");
        Address existingAddress = addressService.get(addressId);
        model.addAttribute("addressAttribute", existingAddress);

        return "address-edit";
    }

    /**
     * Edits an existing record
     *
     * @param addressId
     * @param address
     * @param result    checks if there is errors in the input
     * @param model
     * @return address list view page
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postEdit(@RequestParam("id") Integer addressId,
                           @Valid @ModelAttribute("addressAttribute") Address address, BindingResult result, Model model) {
        logger.debug("Received request to add new Address");

        if (result.hasErrors()) {
            model.addAttribute("addressAttribute", address);

            return "address-edit";
        }

        address.setId(addressId);
        addressService.edit(address);

        return "redirect:/main/address/list";
    }

    /**
     * Sets the address value for users to null if they are bound to the address that to be deleted
     *
     * @param addressId
     */
    public void updateUsers(Integer addressId) {
        List<User> users = userService.getAll();
        for (User user : users) {
            // Create new data transfer object
            if (user.getAddress() != null && user.getAddress().getId() == addressId) {
                userService.edit(user);
            }
        }
    }
}