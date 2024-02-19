export const setResponseToCookie = <T>(key: string, response: any) => {
    // Lưu trữ dữ liệu từ response vào cookie
    const responseData = response;
    const jsonData = JSON.stringify(responseData);
    document.cookie = `${key}=${jsonData}; path=/;`;
  };

export const getDataFromCookie = (key: string): any => {
    // Tách các cookie thành một mảng các cặp key-value
    const cookies = document.cookie.split(';');
    
    // Duyệt qua từng cặp key-value để tìm cookie có key tương ứng
    for (let i = 0; i < cookies.length; i++) {
      const cookie = cookies[i].trim();
      // Kiểm tra xem cookie có bắt đầu bằng key=
      if (cookie.startsWith(`${key}=`)) {
        // Trả về giá trị của cookie
        const cookieValue = cookie.substring(`${key}=`.length, cookie.length);
        return JSON.parse(decodeURIComponent(cookieValue));
      }
    }
    
    // Trả về null nếu không tìm thấy cookie có key tương ứng
    return null;
  };