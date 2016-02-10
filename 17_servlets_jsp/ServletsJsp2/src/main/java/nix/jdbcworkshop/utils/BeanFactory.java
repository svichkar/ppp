/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.utils;

import java.util.ArrayList;
import java.util.List;
import nix.bean.CarBean;
import nix.bean.ClientBean;
import nix.bean.WebUserBean;
import nix.jdbcworkshop.entities.Car;
import nix.jdbcworkshop.entities.CarType;
import nix.jdbcworkshop.entities.Client;
import nix.jdbcworkshop.entities.WebRole;
import nix.jdbcworkshop.entities.WebUser;

/**
 *
 * @author mednorcom
 */
public class BeanFactory {

    public static WebUserBean getWebUserBean(WebUser user) {
        WebRole role = DaoFactoryH2.getWebRoleDaoH2().findWebRoleById(user.getWebRoleId());
        WebUserBean webUserBean = new WebUserBean();
        webUserBean.setRole(role);
        webUserBean.setWebUserId(user.getWebUserId());
        webUserBean.setWebUserLogin(user.getWebUserLogin());
        webUserBean.setWebUserPassword(user.getWebUserPassword());
        return webUserBean;
    }

    public static List<WebUserBean> getWebUserBeans(List<WebUser> users) {
        List<WebUserBean> webUserBeans = new ArrayList<>();
        for (WebUser user : users) {
            webUserBeans.add(getWebUserBean(user));
        }
        return webUserBeans;
    }

    public static ClientBean getClientBean(Client client) {
        WebUserBean webUserBean = getWebUserBean(DaoFactoryH2.getWebUserDaoH2().findWebUserById(client.getWebUserId()));
        ClientBean clientBean = new ClientBean();
        clientBean.setClientId(client.getClientId());
        clientBean.setFirstName(client.getFirstName());
        clientBean.setLastName(client.getLastName());
        clientBean.setWebUserBean(webUserBean);
        return clientBean;
    }

    public static List<ClientBean> getClientBeans(List<Client> clients) {
        List<ClientBean> clientBeans = new ArrayList<>();
        for (Client client : clients) {
            clientBeans.add(getClientBean(client));
        }
        return clientBeans;
    }

    public static CarBean getCarBean(Car car) {
        CarType carType = DaoFactoryH2.getCarTypeDaoH2().findCarTypeById(car.getCarTypeId());
        ClientBean clientBean = getClientBean(DaoFactoryH2.getClientDaoH2()
                .findClientById(car.getClientId()));
        CarBean carBean = new CarBean();
        carBean.setCarId(car.getCarId());
        carBean.setSerialId(car.getSerialId());
        carBean.setCarType(carType);
        carBean.setClientBean(clientBean);
        return carBean;
    }

    public static List<CarBean> getCarBeans(List<Car> cars) {
        List<CarBean> carBeans = new ArrayList<>();
        for (Car car : cars) {
            carBeans.add(getCarBean(car));
        }
        return carBeans;
    }
}
