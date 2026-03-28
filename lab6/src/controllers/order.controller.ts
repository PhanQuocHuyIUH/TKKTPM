import { Request, Response } from "express";
import orderService from "../services/order.service";

export class OrderController {
  async create(req: Request, res: Response) {
    try {
      const { userId, items } = req.body;

      if (!userId || !items || !Array.isArray(items) || items.length === 0) {
        res.status(400).json({ error: "userId and items[] are required" });
        return;
      }

      const order = await orderService.createOrder({ userId, items });
      res.status(201).json(order);
    } catch (error: any) {
      if (error.message?.includes("not found") || error.message?.includes("Insufficient")) {
        res.status(400).json({ error: error.message });
        return;
      }
      console.error("Error creating order:", error);
      res.status(500).json({ error: "Internal server error" });
    }
  }

  async getAll(req: Request, res: Response) {
    try {
      const orders = await orderService.getAllOrders();
      res.json(orders);
    } catch (error) {
      console.error("Error fetching orders:", error);
      res.status(500).json({ error: "Internal server error" });
    }
  }

  async getById(req: Request, res: Response) {
    try {
      const id = Array.isArray(req.params.id) ? req.params.id[0] : req.params.id;
      const order = await orderService.getOrderById(parseInt(id));

      if (!order) {
        res.status(404).json({ error: "Order not found" });
        return;
      }

      res.json(order);
    } catch (error) {
      console.error("Error fetching order:", error);
      res.status(500).json({ error: "Internal server error" });
    }
  }
}

export default new OrderController();
