interface User {
  id: number;
  name: string;
  email: string;
  isActive: boolean;
}

let user1: User = {
  id: 1,
  name: "Mai Xuan Quoc",
  email: "maixuanquoc@example.com",
  isActive: true,
};

console.log(user1);
