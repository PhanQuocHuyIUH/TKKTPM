import { Request, Response } from "express";
import userService from "../services/user.service";

export class UserController {
  async create(req: Request, res: Response) {
    try {
      const { name, email, phone } = req.body;

      if (!name || !email) {
        res.status(400).json({ error: "name and email are required" });
        return;
      }

      const user = await userService.createUser({ name, email, phone });
      res.status(201).json(user);
    } catch (error: any) {
      if (error.code === "P2002") {
        res.status(409).json({ error: "Email already exists" });
        return;
      }
      console.error("Error creating user:", error);
      res.status(500).json({ error: "Internal server error" });
    }
  }

  async getAll(req: Request, res: Response) {
    try {
      const users = await userService.getAllUsers();
      res.json(users);
    } catch (error) {
      console.error("Error fetching users:", error);
      res.status(500).json({ error: "Internal server error" });
    }
  }

  async getById(req: Request, res: Response) {
    try {
      const id = Array.isArray(req.params.id) ? req.params.id[0] : req.params.id;
      const user = await userService.getUserById(parseInt(id));

      if (!user) {
        res.status(404).json({ error: "User not found" });
        return;
      }

      res.json(user);
    } catch (error) {
      console.error("Error fetching user:", error);
      res.status(500).json({ error: "Internal server error" });
    }
  }
}

export default new UserController();
