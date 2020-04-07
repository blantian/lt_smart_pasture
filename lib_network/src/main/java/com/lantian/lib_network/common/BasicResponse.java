package com.lantian.lib_network.common;

/**
 * Created by Sherlock·Holmes on 2020-03-06
 */
public class BasicResponse<T> {

    private int status;
    private String message;
    private T data;
    private int code;
    private PageBean page;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static class PageBean {
        /**
         * firstRow : 0
         * listRows : 10
         * parameter : {"user_id":"29480"}
         * totalRows : 1
         * totalPages : 1
         * rollPage : 3
         * lastSuffix : true
         */

        private int firstRow;
        private int listRows;
        private PageBean.ParameterBean parameter;
        private String totalRows;
        private int totalPages;
        private int rollPage;
        private boolean lastSuffix;

        public int getFirstRow() {
            return firstRow;
        }

        public void setFirstRow(int firstRow) {
            this.firstRow = firstRow;
        }

        public int getListRows() {
            return listRows;
        }

        public void setListRows(int listRows) {
            this.listRows = listRows;
        }

        public PageBean.ParameterBean getParameter() {
            return parameter;
        }

        public void setParameter(PageBean.ParameterBean parameter) {
            this.parameter = parameter;
        }

        public String getTotalRows() {
            return totalRows;
        }

        public void setTotalRows(String totalRows) {
            this.totalRows = totalRows;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getRollPage() {
            return rollPage;
        }

        public void setRollPage(int rollPage) {
            this.rollPage = rollPage;
        }

        public boolean isLastSuffix() {
            return lastSuffix;
        }

        public void setLastSuffix(boolean lastSuffix) {
            this.lastSuffix = lastSuffix;
        }

        public static class ParameterBean {
            /**
             * user_id : 29480
             */
            private String user_id;

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }
        }
    }
}
