package com.zdjc.zdjcyun.mvp.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ClmXlm
 * @create 2019/1/10
 * @Describe
 */
public class MemberMsgEntity {


    /**
     * code : 0
     * msg : 操作成功
     * data : [{"typeName":"监测单位","company":"中大检测","principal":"龙章","members":[{"memberId":7,"memberType":2,"memberName":"龙章","memberCompany":"中大检测","memberEmail":"188230526@qq.com","memberPhone":"13357310920","sectorRole":"项目负责人"},{"memberId":8,"memberType":2,"memberName":"王安正","memberCompany":"中大检测","memberEmail":"563567370@qq.com","memberPhone":"18008461690","sectorRole":"技术负责人"},{"memberId":9,"memberType":2,"memberName":"杨凯","memberCompany":"中大检测","memberEmail":"506492114@qq.com","memberPhone":"17749670460","sectorRole":"施工安全负责人"},{"memberId":10,"memberType":2,"memberName":"李优","memberCompany":"中大检测","memberEmail":"956886503@qq.com","memberPhone":"17608414836","sectorRole":"施工安全负责人"},{"memberId":11,"memberType":2,"memberName":"姚磊","memberCompany":"中大检测","memberEmail":"999@qq.com","memberPhone":"18390878703","sectorRole":"施工安全负责人"},{"memberId":12,"memberType":2,"memberName":"刘涛","memberCompany":"中大检测","memberEmail":"999@qq.com","memberPhone":"18229747968","sectorRole":"现场施工技术员"},{"memberId":13,"memberType":2,"memberName":"唐波","memberCompany":"中大检测","memberEmail":"382990142@qq.com","memberPhone":"17700799991","sectorRole":"现场施工技术员"},{"memberId":14,"memberType":2,"memberName":"丁尚明","memberCompany":"中大检测","memberEmail":"999@qq.com","memberPhone":"15173186808","sectorRole":"现场施工技术员"}]},{"typeName":"施工单位","company":"中建五局","principal":null,"members":[{"memberId":15,"memberType":1,"memberName":"李小静","memberCompany":"中建五局","memberEmail":"343019332@qq.com","memberPhone":"13974873723","sectorRole":"总工"},{"memberId":16,"memberType":1,"memberName":"闫涛","memberCompany":"中建五局","memberEmail":"yantao05@qq.com","memberPhone":"15029669300","sectorRole":"测量主管"}]}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * typeName : 监测单位
         * company : 中大检测
         * principal : 龙章
         * members : [{"memberId":7,"memberType":2,"memberName":"龙章","memberCompany":"中大检测","memberEmail":"188230526@qq.com","memberPhone":"13357310920","sectorRole":"项目负责人"},{"memberId":8,"memberType":2,"memberName":"王安正","memberCompany":"中大检测","memberEmail":"563567370@qq.com","memberPhone":"18008461690","sectorRole":"技术负责人"},{"memberId":9,"memberType":2,"memberName":"杨凯","memberCompany":"中大检测","memberEmail":"506492114@qq.com","memberPhone":"17749670460","sectorRole":"施工安全负责人"},{"memberId":10,"memberType":2,"memberName":"李优","memberCompany":"中大检测","memberEmail":"956886503@qq.com","memberPhone":"17608414836","sectorRole":"施工安全负责人"},{"memberId":11,"memberType":2,"memberName":"姚磊","memberCompany":"中大检测","memberEmail":"999@qq.com","memberPhone":"18390878703","sectorRole":"施工安全负责人"},{"memberId":12,"memberType":2,"memberName":"刘涛","memberCompany":"中大检测","memberEmail":"999@qq.com","memberPhone":"18229747968","sectorRole":"现场施工技术员"},{"memberId":13,"memberType":2,"memberName":"唐波","memberCompany":"中大检测","memberEmail":"382990142@qq.com","memberPhone":"17700799991","sectorRole":"现场施工技术员"},{"memberId":14,"memberType":2,"memberName":"丁尚明","memberCompany":"中大检测","memberEmail":"999@qq.com","memberPhone":"15173186808","sectorRole":"现场施工技术员"}]
         */

        private String typeName;
        private String company;
        private String principal;
        private ArrayList<MembersBean> members;

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getPrincipal() {
            return principal;
        }

        public void setPrincipal(String principal) {
            this.principal = principal;
        }

        public ArrayList<MembersBean> getMembers() {
            return members;
        }

        public void setMembers(ArrayList<MembersBean> members) {
            this.members = members;
        }

        public static class MembersBean {
            /**
             * memberId : 7
             * memberType : 2
             * memberName : 龙章
             * memberCompany : 中大检测
             * memberEmail : 188230526@qq.com
             * memberPhone : 13357310920
             * sectorRole : 项目负责人
             */

            private int memberId;
            private int memberType;
            private String memberName;
            private String memberCompany;
            private String memberEmail;
            private String memberPhone;
            private String sectorRole;

            public int getMemberId() {
                return memberId;
            }

            public void setMemberId(int memberId) {
                this.memberId = memberId;
            }

            public int getMemberType() {
                return memberType;
            }

            public void setMemberType(int memberType) {
                this.memberType = memberType;
            }

            public String getMemberName() {
                return memberName;
            }

            public void setMemberName(String memberName) {
                this.memberName = memberName;
            }

            public String getMemberCompany() {
                return memberCompany;
            }

            public void setMemberCompany(String memberCompany) {
                this.memberCompany = memberCompany;
            }

            public String getMemberEmail() {
                return memberEmail;
            }

            public void setMemberEmail(String memberEmail) {
                this.memberEmail = memberEmail;
            }

            public String getMemberPhone() {
                return memberPhone;
            }

            public void setMemberPhone(String memberPhone) {
                this.memberPhone = memberPhone;
            }

            public String getSectorRole() {
                return sectorRole;
            }

            public void setSectorRole(String sectorRole) {
                this.sectorRole = sectorRole;
            }
        }
    }
}
