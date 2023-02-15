package com.zavyalov.kiselev.coursework.services.roles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleManager {
    static boolean gotConfig = false;
    private static Map<IRole, List<IRule>> roles = new HashMap<>();

    public static void setConfig(Map<IRole, List<IRule>> config) {
        roles = config;
        gotConfig = true;
    }

    public static Map<IRole, List<IRule>> getConfig() {
        if (gotConfig) {
            return roles;
        } else return null;//TODO может быть лучше выбрасывать ошибку?
    }

    public static boolean addRole(IRole newRole) {
        boolean successFlag = false;
        //TODO roleLines.containsKey может быть проблема в том, что оно проверяет не экземпляры а ссылки на них, т. к. для этих классов не переопределен .equals()
        if (roles.containsKey(newRole)) {
            successFlag = false;
            return successFlag;
        } else successFlag = true;
        roles.put(newRole, new ArrayList<>());
        gotConfig = true;
        return successFlag;
    }

    public static boolean addRole(IRole newRole, List<IRule> rules) {
        boolean successFlag = false;
        // TODO roleLines.containsKey может быть проблема в том, что оно проверяет не экземпляры а ссылки на них, т. к. для этих классов не переопределен .equals()
        if (roles.containsKey(newRole)) {
            successFlag = false;
            return successFlag;
        } else successFlag = true;
        roles.put(newRole, rules);
        gotConfig = true;
        return successFlag;
    }

    public static boolean addRules(IRole keyRole, List<IRule> rules) {
        boolean successFlag = false;
        if (roles.containsKey(keyRole)) {
            successFlag = false;
            return successFlag;
        } else {
            List<IRule> oldRules = roles.get(keyRole);
            rules.removeAll(oldRules);
            oldRules.addAll(rules);
            roles.put(keyRole, oldRules);
            successFlag = true;
            return successFlag;
        }

    }

    public static List<IRule> getRules(IRole role) {
        //TODO roleLines.containsKey может быть проблема в том, что оно проверяет не экземпляры а ссылки на них, т. к. для этих классов не переопределен .equals()
        if (roles.containsKey(role)) {
            return roles.get(role);
        } else return null;//TODO может быть лучше выбрасывать ошибку?
    }


}
